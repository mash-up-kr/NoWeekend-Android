package team.noweekend.feature.calendar.mvi

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.persistentMapOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.collections.immutable.toImmutableMap
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.stateIn
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toJavaLocalDateTime
import team.noweekend.core.common.android.base.MVIViewModel
import team.noweekend.core.common.kotlin.extension.CalendarUtils.plusDays
import team.noweekend.core.common.kotlin.extension.YEAR_MONTH_DAY_PATTERN
import team.noweekend.core.common.kotlin.extension.now
import team.noweekend.core.common.kotlin.extension.toFormattedString
import team.noweekend.core.common.kotlin.extension.toLocalDate
import team.noweekend.core.common.ui.calendar.model.CalendarDateOfWeek
import team.noweekend.core.common.ui.calendar.model.CalendarMode
import team.noweekend.core.common.ui.calendar.model.CalendarState
import team.noweekend.core.common.ui.calendar.state.CalendarPagerState.Companion.initialPage
import team.noweekend.core.common.ui.todo.model.Todo
import team.noweekend.core.common.ui.todo.model.TodoType
import team.noweekend.core.domain.usecase.CalendarDataProviderUseCase
import team.noweekend.core.domain.usecase.ChangeCompleteScheduleUseCase
import team.noweekend.core.domain.usecase.CreateAddTaskUseCase
import team.noweekend.core.domain.usecase.DeleteTodoUseCase
import team.noweekend.core.domain.usecase.GetRecommendTodoTagUseCase
import team.noweekend.core.model.alarm.AlarmOption
import team.noweekend.core.model.calendar.DateOfWeek
import team.noweekend.core.model.schedule.Schedule
import team.noweekend.core.model.schedule.ScheduleCategory
import team.noweekend.core.model.schedule.ScheduleCreateParam
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
    private val deleteTodoUseCase: DeleteTodoUseCase,
    private val createAddTaskUseCase: CreateAddTaskUseCase,
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
            is CalendarIntent.InitCalendarWithDate -> initCalendarWithDate(
                initPage = intent.initPage,
                localDate = intent.targetDate,
            )

            is CalendarIntent.ChangeComplete -> changeCompleteSchedule(index = intent.index)
            is CalendarIntent.UpdateCalendarState -> updateCalendarState()
            is CalendarIntent.GetRecommendTodoTagList -> getRecommendTodoTag()
            is CalendarIntent.ClickRecommendTodoTag -> clickRecommendTagTodo(index = intent.index)
            is CalendarIntent.ClickMonthChooser -> clickMonthChooser()
            is CalendarIntent.ClickDirectInput -> clickDirectInput()
            is CalendarIntent.ClickTodoOption -> clickTodoOption(index = intent.index)
            is CalendarIntent.DismissTodo -> dismissTodo()
            is CalendarIntent.EditTodo -> editTodo(index = intent.index)
            is CalendarIntent.DeleteTodo -> deleteTodo(index = intent.index)
            is CalendarIntent.AddSameTodo -> addSameTodo(index = intent.index)
        }
    }

    private fun clickMonthChooser() {
        reduce {
            this.copy(
                monthChooserVisible = this.monthChooserVisible.not(),
            )
        }
    }

    private fun dismissMonthChooser() {
        reduce {
            this.copy(
                monthChooserVisible = false,
            )
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


    /**
     * 1일로 이동
     */
    private suspend fun initCalendarWithDate(initPage: Int, localDate: LocalDate) {
        when (val calendarMode = currentState.calendarMode) {
            CalendarMode.WEEK -> {
                initWeekCalendar(initPage = initPage, currentDate = localDate)
                postSideEffect(sideEffect = CalendarSideEffect.CompleteInitWeekCalendar)
                dismissMonthChooser()
                updateChooserMonth(calendarMode = calendarMode, initPage)
            }

            CalendarMode.MONTH -> {
                initMonthCalendar(initPage = initPage, currentDate = localDate)
                postSideEffect(sideEffect = CalendarSideEffect.CompleteInitMonthCalendar)
                dismissMonthChooser()
                updateChooserMonth(calendarMode = calendarMode, initPage)
            }
        }
    }


    private suspend fun initWeekCalendar(initPage: Int, currentDate: LocalDate = LocalDate.now()) {
        calendarDataProviderUseCase.initWeekCalendar(initPage = initPage, currentDate = currentDate)
        updateCalendarState()
        postSideEffect(sideEffect = CalendarSideEffect.CollectWeekPagerStatePage)

    }

    private suspend fun initMonthCalendar(initPage: Int, currentDate: LocalDate = currentState.chooserMonth) {
        calendarDataProviderUseCase.initMonthCalendar(page = initPage, currentDate)
        updateCalendarState()
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
                    val isHasSelectedDate =
                        currentWeekData.dateOfWeeks.flatten()
                            .any { dateOfWeek: DateOfWeek -> dateOfWeek.localDate == currentState.calendarState.selectedDate }


                    reduce {
                        this.copy(
                            chooserMonth = if (isHasSelectedDate) {
                                val selectedDate =
                                    currentWeekData.dateOfWeeks.flatten()
                                        .find { it.localDate == currentState.calendarState.selectedDate }?.localDate
                                if (selectedDate != null) {
                                    LocalDate(
                                        year = selectedDate.year,
                                        monthNumber = selectedDate.monthNumber,
                                        dayOfMonth = 1,
                                    )
                                } else {
                                    LocalDate(
                                        year = currentWeekData.year,
                                        monthNumber = currentWeekData.month,
                                        dayOfMonth = 1,
                                    )

                                }
                            } else {
                                LocalDate(
                                    year = currentWeekData.year,
                                    monthNumber = currentWeekData.month,
                                    dayOfMonth = 1,
                                )
                            },
                        )
                    }
                }
            }
        }
    }

    private fun updateTodoList(targetDate: LocalDate) {
        val calendarDataFlow = when (currentState.calendarState) {
            is CalendarState.Week -> currentState.calendarWeeksData
            is CalendarState.Month -> currentState.calendarMonthsData
        }.mapNotNull { immutableMap ->
            immutableMap.toList().map { (key, value) ->
                value.calendarDateOfWeeksWithTodoList.flatten()
            }.flatten().find {
                it.calendarDateOfWeek.localDate == targetDate
            }?.todoList
        }.toStateFlow(initialValue = persistentListOf())

        reduce {
            this.copy(
                selectedTodoList = calendarDataFlow,
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
                chooserMonth = targetDate,
            )
        }

        if (currentState.calendarMode == CalendarMode.MONTH) {
            val localDateString = targetDate.toFormattedString(
                LocalDate.YEAR_MONTH_DAY_PATTERN,
            )
            postSideEffect(CalendarSideEffect.NavigateToDetailDate(date = localDateString))
        }
    }

    private fun updateCalendarData() {
        val weekDataFlow = calendarDataProviderUseCase.weeksDate.map { weekData ->
            weekData.toList().associate { (key, value) ->
                key to CalendarWeeksDataWithTodoList(
                    year = value.year,
                    month = value.month,
                    calendarDateOfWeeksWithTodoList = value.dateOfWeeks.map { dateOfWeekList ->
                        dateOfWeekList.map { dateOfWeek ->
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
                    }.toImmutableList(),
                )
            }.toImmutableMap()
        }.toStateFlow(initialValue = persistentMapOf())

        val monthDataFlow = calendarDataProviderUseCase.monthData.map { monthData ->
            monthData.toList().associate { (key, value) ->
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
            }.toImmutableMap()
        }.toStateFlow(initialValue = persistentMapOf())


        reduce {
            this.copy(
                calendarWeeksData = weekDataFlow,
                calendarMonthsData = monthDataFlow,
            )

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
    ) {

        reduce {
            this.copy(
                calendarState = when (calendarMode) {
                    CalendarMode.WEEK -> {
                        val pagerData = this.calendarWeeksData.map { innerMap ->
                            innerMap.toList().associate { (key, value) ->
                                key to value.toCalendarWeeksData()
                            }.toImmutableMap()
                        }.toStateFlow(initialValue = persistentMapOf())


                        CalendarState.Week(
                            mode = calendarMode,
                            selectedDate = calendarDataProviderUseCase.targetDate.value,
                            pagerState = this.calendarPagerState.weekPagerState,
                            pagerData = pagerData,
                        )
                    }


                    CalendarMode.MONTH -> {

                        val pagerData = this.calendarMonthsData.map { innerMap ->
                            innerMap.toList().associate { (key, value) ->
                                key to value.toCalendarWeeksData()
                            }.toImmutableMap()
                        }.toStateFlow(initialValue = persistentMapOf())


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

        val todo = currentState.selectedTodoList.value[index]

        val schedule: Schedule = changeCompleteScheduleUseCase(id = todo.id, isComplete = todo.isDone.not())

        calendarDataProviderUseCase.updateWeeksDataWithSchedule(schedule = schedule)

        reduce {
            this.copy(
                selectedTodoList = currentState.selectedTodoList.map { immutableMap ->
                    immutableMap.mapIndexed { innerIndex, todo ->
                        if (innerIndex == index) todo.copy(isDone = schedule.completed) else todo
                    }.toImmutableList()
                }.toStateFlow(initialValue = persistentListOf()),
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

    private fun clickDirectInput() = execute {
        postSideEffect(CalendarSideEffect.NavigateToAddTodoWithDirectInput)
    }

    private suspend fun editTodo(index: Int) {
        val todo = currentState.selectedTodoList.value[index]
        val schedule = Schedule(
            id = todo.id,
            title = todo.title,
            startTime = todo.startDateTime,
            endTime = todo.endDateTime,
            category = ScheduleCategory.valueOf(todo.todoType.name),
            temperature = todo.temperature,
            alarmOption = AlarmOption.valueOf(todo.alarmOption),
            allDay = false, // 무시
            completed = todo.isDone,
        )
        postSideEffect(CalendarSideEffect.NavigateToEditTodo(schedule = schedule))
    }

    private fun clickTodoOption(index: Int) {
        reduce {
            copy(
                todoOptionVisibility = TodoOptionVisibility(
                    visible = currentState.todoOptionVisibility.visible.not(),
                    todoIndex = index,
                    todoType = currentState.selectedTodoList.value[index].todoType,
                ),
            )
        }
    }

    private fun dismissTodo() {
        reduce {
            copy(
                todoOptionVisibility = this.todoOptionVisibility.copy(visible = false),
            )
        }
    }

    private suspend fun deleteTodo(index: Int) {
        val todo = currentState.selectedTodoList.value[index]
        val todoDate = LocalDateTime.parse(todo.startDateTime).toLocalDate()
        val todoId = todo.id
        deleteTodoUseCase(id = todoId)

        initCalendarWithDate(initPage = initialPage, localDate = todoDate)

        reduce {
            copy(
                todoOptionVisibility = TodoOptionVisibility(
                    visible = false,
                ),
            )
        }
    }

    private suspend fun addSameTodo(index: Int) {
        val todo = currentState.selectedTodoList.value[index]
        val todoStartDate = LocalDateTime.parse(todo.startDateTime).toJavaLocalDateTime()
        val todoEndDate = LocalDateTime.parse(todo.endDateTime).toJavaLocalDateTime()
        val updateStartDateTime = todoStartDate.plusDays(1)
        val updateEndDateTime = todoEndDate.plusDays(1)
        createAddTaskUseCase(
            param = ScheduleCreateParam(
                title = todo.title,
                startDateTime = todo.startDateTime,
                endDateTime = todo.endDateTime,
                category = todo.todoType.name,
                temperature = todo.temperature,
                alarmOption = todo.alarmOption,
            ),
        )

        reduce {
            copy(
                todoOptionVisibility = TodoOptionVisibility(
                    visible = false,
                ),
            )
        }
    }

    private fun <T> Flow<T>.toStateFlow(
        initialValue: T,
    ): StateFlow<T> =
        this.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000L), initialValue = initialValue)
}
