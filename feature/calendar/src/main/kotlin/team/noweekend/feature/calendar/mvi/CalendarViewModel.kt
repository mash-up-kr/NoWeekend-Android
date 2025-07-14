package team.noweekend.feature.calendar.mvi

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.collections.immutable.toImmutableMap
import kotlinx.coroutines.flow.combine
import kotlinx.datetime.LocalDate
import team.noweekend.core.common.android.base.MVIViewModel
import team.noweekend.core.common.kotlin.extension.now
import team.noweekend.core.common.ui.calendar.model.CalendarDateOfWeek
import team.noweekend.core.common.ui.calendar.model.CalendarMode
import team.noweekend.core.common.ui.calendar.model.CalendarWeeksData
import team.noweekend.core.domain.usecase.CalendarDataProviderUseCase
import team.noweekend.feature.calendar.model.mapper.toImageTypeWithId
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(
    private val calendarDataProviderUseCase: CalendarDataProviderUseCase,
    savedStateHandle: SavedStateHandle,
) : MVIViewModel<CalendarIntent, CalendarSideEffect, CalendarUiState>(savedStateHandle = savedStateHandle) {

    override fun createInitialState(savedStateHandle: SavedStateHandle): CalendarUiState {
        return CalendarUiState.default.copy(
            selectedDate = LocalDate.now(),
        )
    }

    override fun handleClientException(throwable: Throwable) {
        TODO("Not yet implemented")
    }

    override suspend fun handleIntent(intent: CalendarIntent) {
        when (intent) {
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
        }
    }

    private fun initCalendar(initPage: Int) = execute {
        when(currentState.calendarMode){
            CalendarMode.WEEK->{
                initWeekCalendar(initPage)
            }
            CalendarMode.MONTH->{
                initMonthCalendar(initPage)
            }
        }

    }

    private suspend fun initWeekCalendar(initPage: Int) {
        calendarDataProviderUseCase.initWeekCalendar(initPage = initPage)
        postSideEffect(sideEffect = CalendarSideEffect.CollectWeekPagerStatePage)

    }

    private suspend fun initMonthCalendar(initPage: Int)  {
        calendarDataProviderUseCase.initMonthCalendar(page = initPage, currentState.chooserMonth)
        postSideEffect(sideEffect = CalendarSideEffect.CollectMonthPagerStatePage)
    }

    private fun updateNextWeeksData(currentPage: Int) = execute {
        calendarDataProviderUseCase.updateNextWeeksData(currentPage = currentPage)
    }

    private fun updatePreviousWeeksData(currentPage: Int) = execute {
        calendarDataProviderUseCase.updatePreviousWeeksData(currentPage = currentPage)
    }

    private fun updateNextMonthsData(currentPage: Int) = execute {
        calendarDataProviderUseCase.updateNextMonthData(currentPage = currentPage)
    }

    private fun updatePreviousMonthsData(currentPage: Int) = execute {
        calendarDataProviderUseCase.updatePreviousMonthData(currentPage = currentPage)
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

    private fun updateTargetDate(calendarDateOfWeek: CalendarDateOfWeek) = execute {
        val imageType = team.noweekend.core.model.calendar.ImageType.valueOf(
            calendarDateOfWeek.calendarImageType.name,
        )
        val dateOfWeekModel = team.noweekend.core.model.calendar.DateOfWeek(
            imageType = imageType,
            localDate = calendarDateOfWeek.localDate,
            isCurrentDate = calendarDateOfWeek.isCurrentDate,
        )
        calendarDataProviderUseCase.updateTargetDate(dateOfWeek = dateOfWeekModel)
        reduce {
            this.copy(
                selectedDate = calendarDateOfWeek.localDate,
            )
        }
    }

    private fun updateCalendarData() = execute {
        val weekDataFlow = calendarDataProviderUseCase.weeksDate
        val monthDataFlow = calendarDataProviderUseCase.monthData
        weekDataFlow.combine(monthDataFlow) { weekData, monthData ->
            weekData to monthData
        }.collect { (weekData, monthData) ->
            reduce {
                this.copy(
                    calendarWeeksData = weekData.toList().associate { (key, value) ->
                        key to CalendarWeeksData(
                            year = value.year,
                            month = value.month,
                            calendarDateOfWeeks = value.dateOfWeeks.map { dateOfWeekList ->
                                val calendarDateOfWeek = dateOfWeekList.map { dateOfWeek ->
                                    CalendarDateOfWeek(
                                        calendarImageType = dateOfWeek.imageType.toImageTypeWithId(),
                                        localDate = dateOfWeek.localDate,
                                        isCurrentDate = dateOfWeek.isCurrentDate,
                                    )
                                }.toImmutableList()
                                calendarDateOfWeek
                            }.toImmutableList(),
                        )
                    }.toImmutableMap(),
                    calendarMonthsData = monthData.toList().associate { (key, value) ->
                        key to CalendarWeeksData(
                            year = value.year,
                            month = value.month,
                            calendarDateOfWeeks = value.dateOfWeeks.map { dateOfWeekList ->
                                val calendarDateOfWeek = dateOfWeekList.map { dateOfWeek ->
                                    CalendarDateOfWeek(
                                        calendarImageType = dateOfWeek.imageType.toImageTypeWithId(),
                                        localDate = dateOfWeek.localDate,
                                        isCurrentDate = dateOfWeek.isCurrentDate,
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

    private fun collectCalendarEvent() = execute {
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

    private fun updateCalendarMode() = execute {
        reduce {
            this.copy(
                calendarMode = if (this.calendarMode == CalendarMode.MONTH) CalendarMode.WEEK else CalendarMode.MONTH,
            )
        }
    }

    private fun updateCalendarMode(isMonth: Boolean) = execute {
        reduce {
            this.copy(
                calendarMode = if (isMonth) CalendarMode.MONTH else CalendarMode.WEEK,
            )
        }
    }

    private fun updateChooserAndSendUpdateCalenderSideEffect(page: Int, calendarMode: CalendarMode) = execute {
        intent(
            CalendarIntent.UpdateChooserMonth(
                page = page,
                calendarMode = calendarMode,
            ),
        )

        when (calendarMode) {
            CalendarMode.WEEK -> {
                postSideEffect(sideEffect = CalendarSideEffect.UpdateWeekCalendar(currentPage = page))
            }

            CalendarMode.MONTH -> {
                postSideEffect(sideEffect = CalendarSideEffect.UpdateMonthCalendar(currentPage = page))
            }
        }
    }

}
