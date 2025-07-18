package team.noweekend.feature.addtask.mvi

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.serialization.json.Json
import team.noweekend.core.common.android.base.MVIViewModel
import team.noweekend.core.common.kotlin.extension.now
import team.noweekend.core.common.kotlin.extension.toDateTimeString
import team.noweekend.core.common.kotlin.extension.toIso8601Z
import team.noweekend.core.design.system.core.component.toggle.ToggleState
import team.noweekend.core.domain.usecase.CreateAddTaskUseCase
import team.noweekend.core.domain.usecase.EditScheduleUseCase
import team.noweekend.core.model.schedule.Schedule
import team.noweekend.core.model.schedule.ScheduleCategory
import team.noweekend.core.model.schedule.ScheduleCreateParam
import team.noweekend.feature.addtask.detail.model.VacationTimeType
import toLocalDate
import toLocalTime
import javax.inject.Inject

@HiltViewModel
class AddTaskViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val createAddTaskUseCase: CreateAddTaskUseCase,
    private val editScheduleUseCase: EditScheduleUseCase,
) : MVIViewModel<AddTaskIntent, AddTaskSideEffect, AddTaskUiState>(savedStateHandle) {
    private val scheduleJson = savedStateHandle.get<String>("id") ?: ""

    init {
        if (scheduleJson.isNotEmpty()) {
            val schedule = if (scheduleJson.isNotEmpty()) Json.decodeFromString<Schedule>(scheduleJson) else null
            schedule?.let {
                reduce {
                    copy(
                        todoId = it.id,
                        taskInfo = taskInfo.copy(
                            title = it.title,
                            selectedType = it.category,
                            isAllDay = it.allDay,
                            startDate = it.startTime.toLocalDate(),
                            endDate = it.endTime.toLocalDate(),
                            startTime = it.startTime.toLocalTime(),
                            endTime = it.endTime.toLocalTime(),
                            temperature = it.temperature ?: 5,
                            selectedVacation = if (it.allDay) VacationTimeType.ALL_DAY else VacationTimeType.MORNING,
                        ),
                    )
                }
            }
        }
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): AddTaskUiState {
        return AddTaskUiState.INITIAL_STATE
    }

    override fun handleClientException(throwable: Throwable) {
        Unit
    }

    override suspend fun handleIntent(intent: AddTaskIntent) {
        when (intent) {
            AddTaskIntent.ClickBackDetail -> postSideEffect(AddTaskSideEffect.NavigateToBack)
            AddTaskIntent.ClickBackMain -> postSideEffect(AddTaskSideEffect.NavigateToCalendar)
            AddTaskIntent.ClickDetail -> postSideEffect(AddTaskSideEffect.NavigateToDetail)
            AddTaskIntent.ClickSave -> saveAddTaskInfo()
            is AddTaskIntent.SelectEndDate -> updateEndDate(intent.date)
            is AddTaskIntent.SelectEndTime -> updateEndTime(intent.date)
            is AddTaskIntent.SelectStartDate -> updateStartDate(intent.date)
            is AddTaskIntent.SelectStartTime -> updateStartTime(intent.date)
            is AddTaskIntent.SelectTaskType -> updateTaskType(intent.taskType)
            is AddTaskIntent.ToggleAllDay -> updateAllDay(intent.toggleState)
            is AddTaskIntent.WriteTemperature -> updateTemperature(intent.temperature)
            is AddTaskIntent.WriteTitle -> updateTitle(intent.title)
            is AddTaskIntent.SelectVacationTime -> updateVacationTime(intent.vacationTimeType)
        }
    }

    private fun updateVacationTime(vacationTimeType: VacationTimeType) {
        reduce {
            copy(
                taskInfo = taskInfo.copy(
                    selectedVacation = vacationTimeType,
                ),
            )
        }
    }

    private fun saveAddTaskInfo() {
        execute {
            with(uiState.value.taskInfo) {
                if (uiState.value.todoId.isEmpty()) {
                    createAddTaskUseCase(
                        param = ScheduleCreateParam(
                            title = title,
                            startDateTime = startDate.toDateTimeString(startTime),
                            endDateTime = endDate.toDateTimeString(endTime),
                            category = selectedType.name,
                            temperature = temperature,
                            alarmOption = "NONE",
                        ),
                    )
                        .onSuccess {
                            postSideEffect(AddTaskSideEffect.ShowSuccessToast)
                            postSideEffect(AddTaskSideEffect.NavigateToCalendar)
                        }
                        .onFailure {
                            postSideEffect(AddTaskSideEffect.ShowErrorToast)
                        }
                } else {
                    editScheduleUseCase(
                        id = uiState.value.todoId,
                        param = ScheduleCreateParam(
                            title = title,
                            startDateTime = startDate.toIso8601Z(startTime),
                            endDateTime = endDate.toIso8601Z(endTime),
                            category = selectedType.name,
                            temperature = temperature,
                            alarmOption = "NONE",
                        ),
                    )
                        .onSuccess {
                            postSideEffect(AddTaskSideEffect.ShowSuccessToast)
                            postSideEffect(AddTaskSideEffect.NavigateToCalendar)
                        }
                        .onFailure {
                            postSideEffect(AddTaskSideEffect.ShowErrorToast)
                        }
                }
            }
        }
    }

    private fun updateEndDate(date: LocalDate) {
        reduce {
            copy(
                taskInfo = taskInfo.copy(
                    endDate = date,
                ),
            )
        }
    }

    private fun updateStartDate(date: LocalDate) {
        reduce {
            copy(
                taskInfo = taskInfo.copy(
                    startDate = date,
                ),
            )
        }
    }

    private fun updateStartTime(time: LocalTime) {
        reduce {
            copy(
                taskInfo = taskInfo.copy(
                    startTime = time,
                ),
            )
        }
    }

    private fun updateEndTime(time: LocalTime) {
        reduce {
            copy(
                taskInfo = taskInfo.copy(
                    endTime = time,
                ),
            )
        }
    }

    private fun updateTaskType(taskType: ScheduleCategory) {
        reduce {
            copy(
                taskInfo = taskInfo.copy(
                    selectedType = taskType,
                ),
            )
        }
    }

    private fun updateAllDay(toggleState: ToggleState) {
        reduce {
            copy(
                taskInfo = taskInfo.copy(
                    isAllDay = toggleState == ToggleState.ON,
                ),
            )
        }

        if (toggleState == ToggleState.OFF) {
            updateStartDate(LocalDate.now())
            updateEndDate(LocalDate.now())
            updateStartTime(LocalTime(0, 0))
            updateEndTime(LocalTime(23, 59, 59))
        }
    }

    private fun updateTemperature(temperature: String) {
        reduce {
            copy(
                taskInfo = taskInfo.copy(
                    temperature = temperature.toIntOrNull() ?: 5, // Default to 5 if parsing fails
                ),
            )
        }
    }

    private fun updateTitle(title: String) {
        reduce {
            copy(
                taskInfo = taskInfo.copy(
                    title = title,
                ),
            )
        }
    }
}
