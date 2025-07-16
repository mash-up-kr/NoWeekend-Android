package team.noweekend.feature.detail.date.mvi

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.datetime.LocalDate
import team.noweekend.core.common.android.base.MVIViewModel
import team.noweekend.core.common.kotlin.extension.parseLocalDateString
import team.noweekend.core.domain.usecase.CalendarDataProviderUseCase
import team.noweekend.core.domain.usecase.ChangeCompleteScheduleUseCase
import team.noweekend.core.model.schedule.Schedule
import team.noweekend.core.model.schedule.ScheduleCategory
import team.noweekend.core.navigator.model.DetailDate
import team.noweekend.feature.detail.date.model.DegreeUIModel
import team.noweekend.feature.detail.date.model.mapper.toTodo
import javax.inject.Inject

@HiltViewModel
class DetailDateViewModel @Inject constructor(
    private val calendarDateProviderUseCase: CalendarDataProviderUseCase,
    private val changeCompleteScheduleUseCase: ChangeCompleteScheduleUseCase,
    savedStateHandle: SavedStateHandle,
) : MVIViewModel<DetailDateIntent, DetailDateSideEffect, DetailDateUiState>(
    savedStateHandle = savedStateHandle,
) {
    override fun createInitialState(savedStateHandle: SavedStateHandle): DetailDateUiState {

        val date = savedStateHandle.toRoute<DetailDate>().date
        val localDate = LocalDate.parseLocalDateString(date)
        return DetailDateUiState(
            dateTitle = date,
            date = localDate,
            degreeUiModel = DegreeUIModel(degree = 0, isAnnualLeave = false),
            todoList = persistentListOf(),
        )
    }

    override fun onCleared() {
        super.onCleared()
    }

    override fun handleClientException(throwable: Throwable) {
    }

    override suspend fun handleIntent(intent: DetailDateIntent) {
        when (intent) {
            is DetailDateIntent.InitState -> initState()
            is DetailDateIntent.ChangeComplete -> changeCompleteSchedule(
                index = intent.index,
            )
        }
    }

    private suspend fun initState() {
        calendarDateProviderUseCase.monthData.collect { weeksDateMap ->
            val scheduleList = weeksDateMap.values.toList().map { weeksData ->
                weeksData.dateOfWeeks.flatten().filter { dateOfWeek ->
                    dateOfWeek.localDate == uiState.value.date
                }.map { dateOfWeek ->
                    dateOfWeek.scheduleList
                }.flatten()
            }.flatten().map { schedule ->
                schedule
            }.toImmutableList()

            val todoList = scheduleList.map { schedule ->
                schedule.toTodo()
            }.toImmutableList()

            reduce {
                this.copy(
                    todoList = todoList,
                    degreeUiModel = DegreeUIModel(
                        degree = scheduleList.filter { schedule -> schedule.completed }
                            .sumOf { schedule -> schedule.temperature },
                        isAnnualLeave = scheduleList.any { schedule -> schedule.category == ScheduleCategory.LEAVE }
                    )
                )
            }
        }
    }

    private suspend fun changeCompleteSchedule(index: Int) {
        val todo = currentState.todoList[index]

        val schedule: Schedule =
            changeCompleteScheduleUseCase(id = todo.id, isComplete = todo.isDone.not())

        calendarDateProviderUseCase.updateMonthsDataWithSchedule(schedule = schedule)

        reduce {
            this.copy(
                todoList = currentState.todoList.mapIndexed { innerIndex, todo ->
                    if (innerIndex == index) todo.copy(
                        isDone = schedule.completed
                    ) else todo
                }.toImmutableList()
            )
        }

    }
}
