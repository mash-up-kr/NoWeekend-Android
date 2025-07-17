package team.noweekend.feature.detail.date.mvi

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.datetime.LocalDate
import team.noweekend.core.common.android.base.MVIViewModel
import team.noweekend.core.common.kotlin.extension.parseLocalDateString
import team.noweekend.core.common.ui.todo.model.Todo
import team.noweekend.core.common.ui.todo.model.TodoType
import team.noweekend.core.domain.usecase.CalendarDataProviderUseCase
import team.noweekend.core.domain.usecase.ChangeCompleteScheduleUseCase
import team.noweekend.core.domain.usecase.GetRecommendTodoTagUseCase
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
    private val getRecommendTodoTagUseCase: GetRecommendTodoTagUseCase,
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
            recommendTodoTagList = persistentListOf(),
        )
    }

    override fun onCleared() {
        super.onCleared()
    }

    override fun handleClientException(throwable: Throwable) {
        throwable.printStackTrace()
    }

    override suspend fun handleIntent(intent: DetailDateIntent) {
        when (intent) {
            is DetailDateIntent.InitState -> initState()
            is DetailDateIntent.ChangeComplete -> changeCompleteSchedule(
                index = intent.index,
            )

            is DetailDateIntent.GetRecommendTodoTagList -> getRecommendTodoTag()
            is DetailDateIntent.ClickRecommendTodoTag -> clickRecommendTodoTag(index = intent.index)
            is DetailDateIntent.ClickBackButton -> clickBackButton()
        }
    }

    private suspend fun initState() {
        calendarDateProviderUseCase.monthData.collect { weeksDateMap ->
            val scheduleList =
                weeksDateMap.values.toList().filter { it.month == uiState.value.date.monthNumber }
                    .flatMap { weeksData ->
                        val flattenWeeksData = weeksData.dateOfWeeks.flatten()

                        val filteredWeeksData = flattenWeeksData.filter { dateOfWeek ->
                            dateOfWeek.localDate == uiState.value.date
                        }
                        val scheduleList = filteredWeeksData.map { dateOfWeek ->
                            dateOfWeek.scheduleList
                        }.flatten()

                        scheduleList.toImmutableList()
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
                        isAnnualLeave = scheduleList.any { schedule -> schedule.category == ScheduleCategory.LEAVE },
                    ),
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
                    if (innerIndex == index) {
                        todo.copy(
                            isDone = schedule.completed,
                        )
                    } else {
                        todo
                    }
                }.toImmutableList(),
            )
        }
    }

    private fun getRecommendTodoTag() = execute {
        val tagList = getRecommendTodoTagUseCase()
        reduce {
            this.copy(
                recommendTodoTagList = tagList.mapIndexed { index, tagName ->
                    Todo(
                        id = tagName,
                        title = tagName,
                        description = "",
                        todoType = when (index) {
                            0 -> TodoType.Company()
                            1 -> TodoType.Personal()
                            else -> TodoType.Etc()
                        },
                        isDone = false,
                    )
                }.toImmutableList(),
            )
        }
    }

    private fun clickRecommendTodoTag(index: Int) = execute {
        val todo = currentState.recommendTodoTagList[index]
        postSideEffect(sideEffect = DetailDateSideEffect.NavigateToAddTodo(todo = todo))
    }

    private fun clickBackButton() = execute {
        postSideEffect(sideEffect = DetailDateSideEffect.NavigateToBack)
    }
}
