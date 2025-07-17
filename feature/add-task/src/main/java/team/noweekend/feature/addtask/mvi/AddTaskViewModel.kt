package team.noweekend.feature.addtask.mvi

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import team.noweekend.core.common.android.base.MVIViewModel
import team.noweekend.core.common.kotlin.extension.now
import team.noweekend.core.common.kotlin.extension.toDateTimeString
import team.noweekend.core.design.system.core.component.toggle.ToggleState
import team.noweekend.core.domain.usecase.CreateAddTaskUseCase
import team.noweekend.core.model.schedule.ScheduleCategory
import team.noweekend.core.model.schedule.ScheduleCreateParam
import javax.inject.Inject

@HiltViewModel
class AddTaskViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val createAddTaskUseCase: CreateAddTaskUseCase,
) : MVIViewModel<AddTaskIntent, AddTaskSideEffect, AddTaskUiState>(savedStateHandle) {

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
        }
    }

    private fun saveAddTaskInfo() {
        execute {
            with(uiState.value.taskInfo) {
                createAddTaskUseCase(
                    param = ScheduleCreateParam(
                        title = title,
                        startDateTime = startDate.toDateTimeString(startTime),
                        endDateTime = endDate.toDateTimeString(endTime),
                        category = selectedType.tag,
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
        Log.d("AddTaskViewModel", "Selected task type: ${taskType}")
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
            updateEndTime(LocalTime(0, 0))
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
