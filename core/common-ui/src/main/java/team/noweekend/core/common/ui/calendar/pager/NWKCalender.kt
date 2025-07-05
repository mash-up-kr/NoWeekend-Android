import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Modifier
import kotlinx.datetime.LocalDate
import team.noweekend.core.common.ui.calendar.component.CalendarItem
import team.noweekend.core.common.ui.calendar.component.DayOfWeekBar
import team.noweekend.core.common.ui.calendar.model.DateOfWeek
import team.noweekend.core.common.ui.calendar.model.WeeksData
import team.noweekend.core.common.ui.calendar.state.CalendarPagerState.CalendarMode

@Composable
fun NWKCalender(
    mode: CalendarMode,
    weekPagerState: PagerState,
    monthPagerState: PagerState,
    selectedDate: State<LocalDate>,
    calendarItemClickable: Boolean,
    monthData: SnapshotStateMap<Int, WeeksData>,
    weeksData: SnapshotStateMap<Int, WeeksData>,
    onClickDateOfWeek: (DateOfWeek) -> Unit,
    modifier: Modifier = Modifier,

) {

    Column(
        modifier = modifier,
    ) {
        DayOfWeekBar(modifier = Modifier.fillMaxWidth(), isMondayStarted = true)
        when (mode) {
            CalendarMode.WEEK -> {
                HorizontalPager(
                    state = weekPagerState,
                    modifier = Modifier.fillMaxWidth(),
                ) { page ->

                    val weekDates = weeksData[page] ?: WeeksData.default

                    CalendarItem(
                        dataList = weekDates,
                        calendarMode = mode,
                        calendarItemClickable = calendarItemClickable,
                        targetDate = selectedDate,
                        onClickDateOfWeek = onClickDateOfWeek,
                    )
                }
            }

            CalendarMode.MONTH -> {
                VerticalPager(
                    state = monthPagerState,
                    modifier = Modifier.fillMaxWidth(),
                ) { page ->

                    val dataList = monthData[page] ?: WeeksData.default

                    CalendarItem(
                        dataList = dataList,
                        calendarMode = mode,
                        calendarItemClickable = calendarItemClickable,
                        targetDate = selectedDate,
                        onClickDateOfWeek = onClickDateOfWeek,
                    )
                }
            }
        }
    }
}

// @Preview
// @Composable
// private fun PreviewCalendar() {
//    val calendarDataProvider = rememberCalendarDataProvider()
//    val calendarPagerState = rememberCalendarPagerState()
//    val calendarMode by calendarPagerState.calendarMode.collectAsState()
//    val coroutineScope = rememberCoroutineScope()
//
//    NWKTheme {
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(color = NWKTheme.color.Neutral.white),
//        ) {
//            CalendarTypeToggle(
//                modifier = Modifier.align(Alignment.End),
//                currentCalendarMode = calendarMode,
//                onToggleStateChanged = { isMonth ->
//                    if (isMonth) {
//                        calendarPagerState.updateCalendarMode(calendarMode = CalendarMode.MONTH)
//                    } else {
//                        calendarPagerState.updateCalendarMode(calendarMode = CalendarMode.WEEK)
//                        coroutineScope.launch {
//                            calendarDataProvider.initWeekCalendar(initPage = calendarPagerState.initialPage)
//                        }
//
//                        calendarPagerState.scrollToInitialWeekPage()
//                    }
//                },
//                onClickToggle = {
//                    calendarPagerState.updateCalendarMode(
//                        calendarMode = if (calendarPagerState.calendarMode.value == CalendarMode.WEEK) {
//                            CalendarMode.MONTH
//                        } else {
//                            CalendarMode.WEEK
//                        },
//                    )
//                },
//
//                )
//            NWKCalender(
//                calendarPagerState = calendarPagerState,
//                calendarDataProvider = calendarDataProvider,
//            )
//        }
//    }
// }
