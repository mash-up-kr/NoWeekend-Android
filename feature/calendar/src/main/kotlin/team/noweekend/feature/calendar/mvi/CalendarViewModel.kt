package team.noweekend.feature.calendar.mvi

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.collections.immutable.toImmutableMap
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.combine
import kotlinx.datetime.LocalDate
import team.noweekend.core.common.android.base.MVIViewModel
import team.noweekend.core.common.kotlin.extension.YEAR_MONTH_DAY_PATTERN
import team.noweekend.core.common.kotlin.extension.toFormattedString
import team.noweekend.core.common.ui.calendar.model.CalendarDateOfWeek
import team.noweekend.core.common.ui.calendar.model.CalendarMode
import team.noweekend.core.common.ui.calendar.model.CalendarState
import team.noweekend.core.common.ui.todo.model.Todo
import team.noweekend.core.common.ui.todo.model.TodoType
import team.noweekend.core.domain.usecase.CalendarDataProviderUseCase
import team.noweekend.core.domain.usecase.ChangeCompleteScheduleUseCase
import team.noweekend.core.domain.usecase.GetRecommendTodoTagUseCase
import team.noweekend.core.model.schedule.Schedule
import team.noweekend.feature.calendar.model.CalendarDateOfWeekWithTodoList
import team.noweekend.feature.calendar.model.CalendarWeeksDataWithTodoList
import team.noweekend.feature.calendar.model.CalendarWeeksDataWithTodoList.Companion.toCalendarWeeksData
import team.noweekend.feature.calendar.model.mapper.toImageTypeWithId
import team.noweekend.feature.calendar.model.mapper.toTodo
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(
    private val calendarDataProviderUseCase: CalendarDataProviderUseCase,
    private val changeCompleteScheduleUseCase: ChangeCompleteScheduleUseCase,
    private val getRecommendTodoTagUseCase: GetRecommendTodoTagUseCase,
    savedStateHandle: SavedStateHandle,
) : MVIViewModel<CalendarIntent, CalendarSideEffect, CalendarUiState>(savedStateHandle = savedStateHandle) {

    override fun createInitialState(savedStateHandle: SavedStateHandle): CalendarUiState {
        return CalendarUiState.Init
    }

    override fun handleClientException(throwable: Throwable) {
        throwable.printStackTrace()
    }

    override suspend fun handleIntent(intent: CalendarIntent) {
        when (intent) {
            is CalendarIntent.UpdateTodoList -> updateTodoList(targetDate = intent.targetDate)
            is CalendarIntent.CollectCalendarEvent -> collectCalendarEvent()
            is CalendarIntent.UpdateCalendarDataAndChooser -> updateChooserAndSendUpdateCalenderSideEffect(
                page = intent.page,
                calendarMode = intent.calendarMode,
            )

            is CalendarIntent.UpdateCalendarData -> updateCalendarData()
            is CalendarIntent.UpdateTargetDate -> updateTargetDate(calendarDateOfWeek = intent.calendarDateOfWeek)
            is CalendarIntent.UpdateChooserMonth -> updateChooserMonth(
                calendarMode = intent.calendarMode,
                page = intent.page,
            )

            is CalendarIntent.UpdateNextWeeksData -> updateNextWeeksData(currentPage = intent.page)
            is CalendarIntent.UpdatePreviousWeeksData -> updatePreviousWeeksData(currentPage = intent.page)
            is CalendarIntent.UpdateNextMonthsData -> updateNextMonthsData(currentPage = intent.page)
            is CalendarIntent.UpdatePreviousMonthsData -> updatePreviousMonthsData(currentPage = intent.page)
            is CalendarIntent.UpdateCalendarMode -> updateCalendarMode()
            is CalendarIntent.UpdateCalendarModeWithToggleState -> updateCalendarMode(isMonth = intent.isMonth)
            is CalendarIntent.InitCalendar -> initCalendar(initPage = intent.initPage)
            is CalendarIntent.ChangeComplete -> changeCompleteSchedule(index = intent.index)
            is CalendarIntent.UpdateCalendarState -> updateCalendarState()
            is CalendarIntent.GetRecommendTodoTagList -> getRecommendTodoTag()
            is CalendarIntent.ClickRecommendTodoTag -> clickRecommendTagTodo(index = intent.index)
        }
    }

    private suspend fun initCalendar(initPage: Int) {
        when (currentState.calendarMode) {
            CalendarMode.WEEK -> {
                initWeekCalendar(initPage)
            }

            CalendarMode.MONTH -> {
                initMonthCalendar(initPage)
            }
        }

    }

    private suspend fun initWeekCalendar(initPage: Int) {
        calendarDataProviderUseCase.initWeekCalendar(initPage = initPage)
        updateCalendarState(
            initFirstTodoList = true,
        )
        postSideEffect(sideEffect = CalendarSideEffect.CollectWeekPagerStatePage)

    }

    private suspend fun initMonthCalendar(initPage: Int) {
        calendarDataProviderUseCase.initMonthCalendar(page = initPage, currentState.chooserMonth)
        updateCalendarState(
            initFirstTodoList = true,
        )
        postSideEffect(sideEffect = CalendarSideEffect.CollectMonthPagerStatePage)
    }

    private suspend fun updateNextWeeksData(currentPage: Int) = coroutineScope {
        calendarDataProviderUseCase.updateNextWeeksData(currentPage = currentPage)
        updateCalendarState()
    }

    private suspend fun updatePreviousWeeksData(currentPage: Int) = coroutineScope {
        calendarDataProviderUseCase.updatePreviousWeeksData(currentPage = currentPage)
        updateCalendarState()
    }

    private suspend fun updateNextMonthsData(currentPage: Int) = coroutineScope {
        calendarDataProviderUseCase.updateNextMonthData(currentPage = currentPage)
        updateCalendarState()
    }

    private suspend fun updatePreviousMonthsData(currentPage: Int) = coroutineScope {
        calendarDataProviderUseCase.updatePreviousMonthData(currentPage = currentPage)
        updateCalendarState()
    }

    private fun updateChooserMonth(
        calendarMode: CalendarMode,
        page: Int,
    ) = execute {
        when (calendarMode) {
            CalendarMode.MONTH -> {
                val currentMonthData = calendarDataProviderUseCase.getMonthData(page = page)
                if (currentMonthData != null) {
                    reduce {
                        this.copy(
                            chooserMonth = LocalDate(
                                year = currentMonthData.year,
                                monthNumber = currentMonthData.month,
                                dayOfMonth = 1,
                            ),
                        )
                    }
                }
            }

            CalendarMode.WEEK -> {
                val currentWeekData = calendarDataProviderUseCase.getWeeksData(page = page)
                if (currentWeekData != null) {

                    reduce {
                        this.copy(
                            chooserMonth = LocalDate(
                                year = currentWeekData.year,
                                monthNumber = currentWeekData.month,
                                dayOfMonth = 1,
                            ),
                        )
                    }
                }
            }
        }
    }

    private fun updateTodoList(targetDate: LocalDate) {
        val calendarData = when (currentState.calendarState) {
            is CalendarState.Week -> currentState.calendarWeeksData
            is CalendarState.Month -> currentState.calendarMonthsData
        }.map { it.value.calendarDateOfWeeksWithTodoList.flatten() }.flatten().find {
            it.calendarDateOfWeek.localDate == targetDate
        }?.todoList ?: persistentListOf()


        reduce {
            this.copy(
                selectedTodoList = calendarData,
            )
        }
    }

    private suspend fun updateTargetDate(calendarDateOfWeek: CalendarDateOfWeek) {
        val targetDate = calendarDateOfWeek.localDate
        calendarDataProviderUseCase.updateTargetDate(localDate = targetDate)


        val calendarState = when (currentState.calendarState) {
            is CalendarState.Week -> (currentState.calendarState as CalendarState.Week).copy(
                selectedDate = targetDate,
            )

            is CalendarState.Month -> (currentState.calendarState as CalendarState.Month).copy(
                selectedDate = targetDate,
            )
        }

        reduce {
            this.copy(
                calendarState = calendarState,
            )
        }

        if (currentState.calendarMode == CalendarMode.MONTH) {
            val localDateString = targetDate.toFormattedString(
                LocalDate.YEAR_MONTH_DAY_PATTERN,
            )
            postSideEffect(CalendarSideEffect.NavigateToDetailDate(date = localDateString))
        }
    }

    private suspend fun updateCalendarData() {
        val weekDataFlow = calendarDataProviderUseCase.weeksDate
        val monthDataFlow = calendarDataProviderUseCase.monthData
        weekDataFlow.combine(monthDataFlow) { weekData, monthData ->
            weekData to monthData
        }.collect { (weekData, monthData) ->
            reduce {
                this.copy(
                    calendarWeeksData = weekData.toList().associate { (key, value) ->
                        key to CalendarWeeksDataWithTodoList(
                            year = value.year,
                            month = value.month,
                            calendarDateOfWeeksWithTodoList = value.dateOfWeeks.map { dateOfWeekList ->
                                val calendarDateOfWeek = dateOfWeekList.map { dateOfWeek ->
                                    CalendarDateOfWeekWithTodoList(
                                        calendarDateOfWeek = CalendarDateOfWeek(
                                            calendarImageType = dateOfWeek.imageType.toImageTypeWithId(),
                                            localDate = dateOfWeek.localDate,
                                            isCurrentDate = dateOfWeek.isCurrentDate,
                                        ),
                                        todoList = dateOfWeek.scheduleList.map { schedule ->
                                            schedule.toTodo()
                                        }.toImmutableList(),
                                    )
                                }.toImmutableList()
                                calendarDateOfWeek
                            }.toImmutableList(),
                        )
                    }.toImmutableMap(),
                    calendarMonthsData = monthData.toList().associate { (key, value) ->
                        key to CalendarWeeksDataWithTodoList(
                            year = value.year,
                            month = value.month,
                            calendarDateOfWeeksWithTodoList = value.dateOfWeeks.map { dateOfWeekList ->
                                val calendarDateOfWeek = dateOfWeekList.map { dateOfWeek ->
                                    CalendarDateOfWeekWithTodoList(
                                        calendarDateOfWeek = CalendarDateOfWeek(
                                            calendarImageType = dateOfWeek.imageType.toImageTypeWithId(),
                                            localDate = dateOfWeek.localDate,
                                            isCurrentDate = dateOfWeek.isCurrentDate,
                                        ),
                                        todoList = dateOfWeek.scheduleList.map { schedule: Schedule ->
                                            schedule.toTodo()
                                        }.toImmutableList(),
                                    )

                                }.toImmutableList()
                                calendarDateOfWeek
                            }.toImmutableList(),
                        )
                    }.toImmutableMap(),
                )
            }
        }
    }

    private suspend fun collectCalendarEvent() {
        calendarDataProviderUseCase.calendarDataProviderEventFlow.collect { eventFlow ->
            when (eventFlow) {
                CalendarDataProviderUseCase.CalendarDataProviderEvent.CompleteInitMonthCalendar -> {
                    postSideEffect(CalendarSideEffect.CompleteInitMonthCalendar)
                }

                CalendarDataProviderUseCase.CalendarDataProviderEvent.CompleteInitWeeksCalendar -> {
                    postSideEffect(CalendarSideEffect.CompleteInitWeekCalendar)
                }
            }
        }
    }

    private fun updateCalendarState(
        calendarMode: CalendarMode = currentState.calendarMode,
        initFirstTodoList: Boolean = false,
    ) {

        val selectedTodoList = (if (initFirstTodoList) {
            when (calendarMode) {
                CalendarMode.MONTH -> {
                    currentState.calendarMonthsData.map { it.value.calendarDateOfWeeksWithTodoList.flatten() }
                        .flatten().find {
                            it.calendarDateOfWeek.localDate == calendarDataProviderUseCase.targetDate.value
                        }?.todoList
                }

                CalendarMode.WEEK -> {
                    currentState.calendarWeeksData.map { it.value.calendarDateOfWeeksWithTodoList.flatten() }
                        .flatten().find {
                            it.calendarDateOfWeek.localDate == calendarDataProviderUseCase.targetDate.value
                        }?.todoList
                }
            }
        } else currentState.selectedTodoList) ?: currentState.selectedTodoList



        reduce {
            this.copy(
                selectedTodoList = selectedTodoList,
                calendarState = when (calendarMode) {
                    CalendarMode.WEEK -> {
                        val pagerData = this.calendarWeeksData.toList().associate { (key, value) ->
                            key to value.toCalendarWeeksData()
                        }.toImmutableMap()

                        CalendarState.Week(
                            mode = calendarMode,
                            selectedDate = calendarDataProviderUseCase.targetDate.value,
                            pagerState = this.calendarPagerState.weekPagerState,
                            pagerData = pagerData,
                        )
                    }


                    CalendarMode.MONTH -> {
                        val pagerData = this.calendarMonthsData.toList()
                            .associate { (key, value) ->
                                key to value.toCalendarWeeksData()
                            }.toImmutableMap()

                        CalendarState.Month(
                            mode = calendarMode,
                            selectedDate = this.calendarState.selectedDate,
                            pagerState = this.calendarPagerState.monthPagerState,
                            pagerData = pagerData,
                        )
                    }
                },
            )
        }
    }

    private fun updateCalendarMode() {
        updateCalendarMode(isMonth = currentState.calendarMode == CalendarMode.WEEK)
    }

    private fun updateCalendarMode(isMonth: Boolean) {

        val updatedCalendarMode = if (isMonth) CalendarMode.MONTH else CalendarMode.WEEK

        reduce {
            this.copy(
                calendarMode = updatedCalendarMode,
            )
        }
    }

    private suspend fun updateChooserAndSendUpdateCalenderSideEffect(
        page: Int,
        calendarMode: CalendarMode,
    ) {
        intent(
            CalendarIntent.UpdateChooserMonth(
                page = page,
                calendarMode = calendarMode,
            ),
        )

        when (calendarMode) {
            CalendarMode.WEEK -> {
                postSideEffect(sideEffect = CalendarSideEffect.UpdateWeekCalendarPage(currentPage = page))
            }

            CalendarMode.MONTH -> {
                postSideEffect(sideEffect = CalendarSideEffect.UpdateMonthCalendarPage(currentPage = page))
            }
        }
    }


    private suspend fun changeCompleteSchedule(index: Int) {

        val todo = currentState.selectedTodoList[index]

        val schedule: Schedule = changeCompleteScheduleUseCase(id = todo.id, isComplete = todo.isDone.not())

        calendarDataProviderUseCase.updateWeeksDataWithSchedule(schedule = schedule)

        reduce {
            this.copy(
                selectedTodoList = currentState.selectedTodoList.mapIndexed { innerIndex, todo ->
                    if (innerIndex == index) todo.copy(isDone = schedule.completed) else todo
                }.toImmutableList(),
            )
        }
    }

    private fun getRecommendTodoTag() = execute {
        val tagList = getRecommendTodoTagUseCase()
        reduce {
            this.copy(
                recommendTodoList = tagList.mapIndexed { index, tagName ->
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

    private fun clickRecommendTagTodo(index: Int) = execute {
        val todo = currentState.recommendTodoList[index]
        postSideEffect(CalendarSideEffect.NavigateToAddTodo(todo = todo))
    }
}
