package team.noweekend.core.common.ui.calendar.model

import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import kotlinx.collections.immutable.ImmutableMap
import kotlinx.datetime.LocalDate

@Stable
sealed interface CalendarState {
    val mode: CalendarMode
    val pagerState: PagerState
    val calendarItemClickable: Boolean
    val pagerData: ImmutableMap<Int, CalendarWeeksData>
    val selectedDate: State<LocalDate>

    @Stable
    data class Week(
        override val mode: CalendarMode = CalendarMode.WEEK,
        override val pagerState: PagerState,
        override val calendarItemClickable: Boolean = true,
        override val pagerData: ImmutableMap<Int, CalendarWeeksData>,
        override val selectedDate: State<LocalDate>,
    ) : CalendarState

    @Stable
    data class Month(
        override val mode: CalendarMode = CalendarMode.MONTH,
        override val pagerState: PagerState,
        override val calendarItemClickable: Boolean = true,
        override val pagerData: ImmutableMap<Int, CalendarWeeksData>,
        override val selectedDate: State<LocalDate>,
    ) : CalendarState
}
