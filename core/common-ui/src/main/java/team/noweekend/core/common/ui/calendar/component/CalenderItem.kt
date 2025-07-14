package team.noweekend.core.common.ui.calendar.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import kotlinx.datetime.LocalDate
import team.noweekend.core.common.ui.calendar.model.CalendarDateOfWeek
import team.noweekend.core.common.ui.calendar.model.CalendarMode
import team.noweekend.core.common.ui.calendar.model.CalendarWeeksData
import team.noweekend.core.common.ui.calendar.state.CalendarPagerState

@Composable
internal fun CalendarItem(
    calendarMode: CalendarMode,
    dataList: CalendarWeeksData,
    targetDate: State<LocalDate>,
    calendarItemClickable: Boolean,
    modifier: Modifier = Modifier,
    onClickDateOfWeek: (CalendarDateOfWeek) -> Unit = {},
) {
    Column(modifier = modifier) {
        dataList.calendarDateOfWeeks.forEachIndexed { index, dateOfWeeks ->
            key(index) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    dateOfWeeks.forEach { dateOfWeek ->
                        key(dateOfWeek) {
                            CalendarDay(
                                modifier = Modifier.weight(1f),
                                calendarDateOfWeek = dateOfWeek,
                                calendarDayClickable = calendarItemClickable,
                                isSelectedDay = dateOfWeek.localDate == targetDate.value,
                                isCurrentMonth = dateOfWeek.localDate.monthNumber == dataList.month,
                                calendarMode = calendarMode,
                                onClickDateOfWeek = onClickDateOfWeek,
                            )
                        }
                    }
                }
            }
        }
    }
}
