package team.noweekend.feature.calendar.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import team.noweekend.core.common.ui.calendar.CalendarDataProvider
import team.noweekend.core.common.ui.calendar.model.WeeksData
import team.noweekend.core.common.ui.calendar.rememberCalendarDataProvider
import team.noweekend.core.common.ui.calendar.state.CalendarPagerState
import team.noweekend.core.common.ui.calendar.state.rememberCalendarPagerState
import team.noweekend.core.design.system.foundation.theme.NWKTheme

@Composable
internal fun CalendarRoute(
    modifier: Modifier = Modifier,
) {

    val calendarDataProvider = rememberCalendarDataProvider()
    val calendarPagerState = rememberCalendarPagerState()
    val selectedDate = calendarDataProvider.targetDate
    val chooserMonth = remember {
        mutableStateOf(selectedDate.value)
    }
    val calendarMode: CalendarPagerState.CalendarMode by calendarPagerState.calendarMode.collectAsState()


    LaunchedEffect(Unit) {
        calendarDataProvider.calendarDataProviderEventFlow.collect { event ->
            when (event) {
                is CalendarDataProvider.CalendarDataProviderEvent.CompleteInitWeeksCalendar -> {
                    calendarPagerState.scrollToInitialWeekPage()
                }

                is CalendarDataProvider.CalendarDataProviderEvent.CompleteInitMonthCalendar -> {
                    calendarPagerState.scrollToMonthPage()
                }
            }
        }
    }

    LaunchedEffect(calendarMode) {
        when (calendarMode) {
            CalendarPagerState.CalendarMode.WEEK -> {
                launch {
                    calendarDataProvider.initWeekCalendar(initPage = calendarPagerState.initialPage)
                    snapshotFlow {
                        calendarPagerState.weekPagerState.currentPage
                    }.collect { currentPage ->

                        val currentWeekData: WeeksData? = calendarDataProvider.weeksData[currentPage]
                        if (currentWeekData != null) {
                            chooserMonth.value = LocalDate(
                                year = currentWeekData.year,
                                monthNumber = currentWeekData.month,
                                dayOfMonth = 1,
                            )
                        }


                        calendarPagerState.updateWeekCalendar(
                            currentPage = currentPage,
                            updatePreviousWeekPage = calendarDataProvider::updatePreviousWeeksData,
                            updateNextWeekPage = calendarDataProvider::updateNextWeeksData,
                        )
                    }
                }

            }

            CalendarPagerState.CalendarMode.MONTH -> {
                launch {
                    calendarDataProvider.initMonthCalendar(
                        page = calendarPagerState.initialPage,
                        chooserMonth = chooserMonth.value,
                    )
                    snapshotFlow { calendarPagerState.monthPagerState.currentPage }.collect { currentPage ->

                        val currentWeekData: WeeksData? = calendarDataProvider.monthData[currentPage]
                        if (currentWeekData != null) {
                            chooserMonth.value = LocalDate(
                                year = currentWeekData.year,
                                monthNumber = currentWeekData.month,
                                dayOfMonth = 1,
                            )
                        }

                        calendarPagerState.updateMonthCalendar(
                            currentPage = currentPage,
                            updateNextMonthPage = calendarDataProvider::updateNextMonthData,
                            updatePreviousMonthPage = calendarDataProvider::updatePreviousMonthData,
                        )
                    }
                }
            }
        }
    }


    CalendarScreen(
        modifier = modifier,
        weekPagerState = calendarPagerState.weekPagerState,
        monthPagerState = calendarPagerState.monthPagerState,
        mode = calendarMode,
        selectedDate = selectedDate,
        chooserDate = chooserMonth,
        onToggleStateChanged = calendarPagerState::updateCalendarMode,
        monthData = calendarDataProvider.monthData,
        weeksData = calendarDataProvider.weeksData,
        onClickToggle = calendarPagerState::updateCalendarMode,
        onClickDateOfWeek = calendarDataProvider::updateTargetDate,
        onClickYearMonthButton = {},
    )
}

@Preview
@Composable
private fun CalendarRoutePreview() {
    NWKTheme {
        CalendarRoute(
            modifier = Modifier.fillMaxSize(),
        )
    }
}
