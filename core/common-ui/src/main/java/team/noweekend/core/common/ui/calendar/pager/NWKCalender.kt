import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.collections.immutable.toImmutableMap
import team.noweekend.core.common.ui.calendar.component.DayOfWeekBar
import team.noweekend.core.common.ui.calendar.model.CalendarDateOfWeek
import team.noweekend.core.common.ui.calendar.model.CalendarMode
import team.noweekend.core.common.ui.calendar.model.CalendarState
import team.noweekend.core.common.ui.calendar.pager.CalendarPager
import team.noweekend.core.common.ui.calendar.rememberCalendarDataProvider
import team.noweekend.core.common.ui.calendar.state.CalendarPagerState.Companion.initialPage
import team.noweekend.core.common.ui.calendar.state.rememberCalendarPagerState
import team.noweekend.core.design.system.foundation.theme.NWKTheme

@Composable
fun NWKCalender(
    calendarState: CalendarState,
    onClickDateOfWeek: (CalendarDateOfWeek) -> Unit,
    userScrollEnabled: Boolean,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        DayOfWeekBar(modifier = Modifier.fillMaxWidth(), isMondayStarted = true)
        CalendarPager(
            calendarState = calendarState,
            onClickDateOfWeek = onClickDateOfWeek,
            modifier = Modifier.fillMaxWidth(),
            userScrollEnabled = userScrollEnabled,
        )
    }
}

@Preview
@Composable
private fun PreviewNoneScrollCalendar() {
    NWKTheme {
        val calendarDataProvider = rememberCalendarDataProvider()
        val calendarPagerState = rememberCalendarPagerState()

        LaunchedEffect(Unit) {
            calendarDataProvider.initWeekCalendar(initPage = initialPage)
        }
        NWKCalender(
            calendarState = CalendarState.Week(
                pagerData = calendarDataProvider.calendarWeeksData.toImmutableMap(),
                pagerState = calendarPagerState.weekPagerState,
                selectedDate = calendarDataProvider.targetDate.value,
                mode = CalendarMode.WEEK,
            ),
            onClickDateOfWeek = {
                println(it.toString())
            },
            userScrollEnabled = false,
        )
    }
}
