import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import team.noweekend.core.common.ui.calendar.CalendarDataProvider
import team.noweekend.core.common.ui.calendar.CalendarDataProvider.CalendarDataProviderEvent
import team.noweekend.core.common.ui.calendar.component.CalendarItem
import team.noweekend.core.common.ui.calendar.component.CalendarTypeToggle
import team.noweekend.core.common.ui.calendar.component.DayOfWeekBar
import team.noweekend.core.common.ui.calendar.model.WeeksData
import team.noweekend.core.common.ui.calendar.rememberCalendarDataProvider
import team.noweekend.core.common.ui.calendar.state.CalendarPagerState
import team.noweekend.core.common.ui.calendar.state.CalendarPagerState.CalendarMode
import team.noweekend.core.common.ui.calendar.state.rememberCalendarPagerState
import team.noweekend.core.design.system.foundation.theme.NWKTheme

@Composable
fun NWKCalender(
    modifier: Modifier = Modifier,
    calendarPagerState: CalendarPagerState = rememberCalendarPagerState(),
    calendarDataProvider: CalendarDataProvider = rememberCalendarDataProvider(),
) {
    val mode by calendarPagerState.calendarMode.collectAsState()

    LaunchedEffect(Unit) {
        calendarDataProvider.calendarDataProviderEventFlow.collect { event ->
            when (event) {
                is CalendarDataProviderEvent.CompleteInitWeeksCalendar -> {
                    calendarPagerState.scrollToInitialWeekPage()
                }

                is CalendarDataProviderEvent.CompleteInitMonthCalendar -> {
                    calendarPagerState.scrollToMonthPage()
                }
            }
        }
    }

    Column(
        modifier = modifier,
    ) {
        DayOfWeekBar(modifier = Modifier.fillMaxWidth(), isMondayStarted = true)
        when (mode) {
            CalendarMode.WEEK -> {
                LaunchedEffect(Unit) {
                    calendarDataProvider.initWeekCalendar(
                        initPage = calendarPagerState.initialPage,
                    )
                }
                LaunchedEffect(calendarPagerState.weekPagerState.currentPage) {
                    val currentPage = calendarPagerState.weekPagerState.currentPage
                    calendarPagerState.updateWeekCalendar(
                        currentPage = currentPage,
                        updatePreviousWeekPage = calendarDataProvider::updatePreviousWeeksData,
                        updateNextWeekPage = calendarDataProvider::updateNextWeeksData,
                    )
                }

                HorizontalPager(
                    state = calendarPagerState.weekPagerState,
                    modifier = Modifier.fillMaxWidth(),
                ) { page ->

                    val weekDates = calendarDataProvider.weeksData[page] ?: WeeksData.default

                    CalendarItem(
                        dataList = weekDates,
                        calendarMode = mode,
                        targetDate = calendarDataProvider.targetDate,
                        onClickDateOfWeek = calendarDataProvider::updateTargetDate,
                    )
                }
            }

            CalendarMode.MONTH -> {
                LaunchedEffect(Unit) {
                    calendarDataProvider.initMonthCalendar(page = calendarPagerState.initialPage)
                }

                LaunchedEffect(calendarPagerState.monthPagerState.currentPage) {
                    calendarPagerState.updateMonthCalendar(
                        currentPage = calendarPagerState.monthPagerState.currentPage,
                        updateNextMonthPage = calendarDataProvider::updateNextMonthData,
                        updatePreviousMonthPage = calendarDataProvider::updatePreviousMonthData,
                    )
                }
                VerticalPager(
                    state = calendarPagerState.monthPagerState,
                    modifier = Modifier.fillMaxWidth(),
                ) { page ->

                    val dataList = calendarDataProvider.monthData[page] ?: WeeksData.default

                    CalendarItem(
                        dataList = dataList,
                        calendarMode = mode,
                        targetDate = calendarDataProvider.targetDate,
                        onClickDateOfWeek = calendarDataProvider::updateTargetDate,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewCalendar() {
    val calendarDataProvider = rememberCalendarDataProvider()
    val calendarPagerState = rememberCalendarPagerState()
    val calendarMode by calendarPagerState.calendarMode.collectAsState()

    NWKTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = NWKTheme.color.Neutral.white),
        ) {
            CalendarTypeToggle(
                modifier = Modifier.align(Alignment.End),
                currentCalendarMode = calendarMode,
                onToggleStateChanged = { isMonth ->
                    if (isMonth) {
                        calendarPagerState.updateCalendarMode(calendarMode = CalendarMode.MONTH)
                    } else {
                        calendarPagerState.updateCalendarMode(calendarMode = CalendarMode.WEEK)
                        calendarDataProvider.initWeekCalendar(initPage = calendarPagerState.initialPage)
                        calendarPagerState.scrollToInitialWeekPage()
                    }
                },
                onClickToggle = {
                    calendarPagerState.updateCalendarMode(
                        calendarMode = if (calendarPagerState.calendarMode.value == CalendarMode.WEEK) {
                            CalendarMode.MONTH
                        } else {
                            CalendarMode.WEEK
                        },
                    )
                },

            )
            NWKCalender(
                calendarPagerState = calendarPagerState,
                calendarDataProvider = calendarDataProvider,
            )
        }
    }
}
