package team.noweekend.core.common.ui.calendar.pager

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import team.noweekend.core.common.ui.calendar.component.CalendarItem
import team.noweekend.core.common.ui.calendar.model.CalendarState
import team.noweekend.core.common.ui.calendar.model.DateOfWeek
import team.noweekend.core.common.ui.calendar.model.WeeksData

@Composable
internal fun CalendarPager(
    calendarState: CalendarState,
    onClickDateOfWeek: (DateOfWeek) -> Unit,
    modifier: Modifier = Modifier,
    userScrollEnabled: Boolean = true,
) {
    when (calendarState) {
        is CalendarState.Week -> {
            HorizontalPager(
                state = calendarState.pagerState,
                modifier = modifier.fillMaxWidth(),
                userScrollEnabled = userScrollEnabled,
            ) { page ->

                val weekDates = calendarState.pagerData[page] ?: WeeksData.default

                CalendarItem(
                    dataList = weekDates,
                    calendarMode = calendarState.mode,
                    calendarItemClickable = calendarState.calendarItemClickable,
                    targetDate = calendarState.selectedDate,
                    onClickDateOfWeek = onClickDateOfWeek,
                )
            }
        }

        is CalendarState.Month -> {
            VerticalPager(
                state = calendarState.pagerState,
                modifier = modifier.fillMaxWidth(),
                userScrollEnabled = userScrollEnabled,
            ) { page ->

                val monthWeekDates = calendarState.pagerData[page] ?: WeeksData.default

                CalendarItem(
                    dataList = monthWeekDates,
                    calendarMode = calendarState.mode,
                    calendarItemClickable = calendarState.calendarItemClickable,
                    targetDate = calendarState.selectedDate,
                    onClickDateOfWeek = onClickDateOfWeek,
                )
            }
        }
    }
}
