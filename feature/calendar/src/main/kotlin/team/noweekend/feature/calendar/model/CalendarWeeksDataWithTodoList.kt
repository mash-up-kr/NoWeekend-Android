package team.noweekend.feature.calendar.model

import androidx.compose.runtime.Immutable
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import team.noweekend.core.common.ui.calendar.model.CalendarWeeksData

@Immutable
data class CalendarWeeksDataWithTodoList(
    val year: Int,
    val month: Int,
    val calendarDateOfWeeksWithTodoList: ImmutableList<ImmutableList<CalendarDateOfWeekWithTodoList>>,
) {
    companion object {
        fun CalendarWeeksDataWithTodoList.toCalendarWeeksData(): CalendarWeeksData {
            return CalendarWeeksData(
                year = year,
                month = month,
                calendarDateOfWeeks = this.calendarDateOfWeeksWithTodoList.map { calendarDateOfWeeksWithTodoList ->
                    calendarDateOfWeeksWithTodoList.map { calendarDateOfWeekWithTodoList ->
                        calendarDateOfWeekWithTodoList.calendarDateOfWeek
                    }.toImmutableList()
                }.toImmutableList(),
            )
        }
    }
}
