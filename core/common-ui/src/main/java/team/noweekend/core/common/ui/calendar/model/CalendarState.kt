package team.noweekend.core.common.ui.calendar.model

import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.snapshots.SnapshotStateMap
import kotlinx.datetime.LocalDate
import team.noweekend.core.common.ui.calendar.state.CalendarPagerState.CalendarMode

@Stable
sealed interface CalendarState {
    val mode: CalendarMode
    val pagerState: PagerState
    val calendarItemClickable: Boolean
    val pagerData: SnapshotStateMap<Int, WeeksData>
    val selectedDate: State<LocalDate>

    data class Week(
        override val mode: CalendarMode = CalendarMode.WEEK,
        override val pagerState: PagerState,
        override val calendarItemClickable: Boolean = true,
        override val pagerData: SnapshotStateMap<Int, WeeksData>,
        override val selectedDate: State<LocalDate>,
    ) : CalendarState

    data class Month(
        override val mode: CalendarMode = CalendarMode.MONTH,
        override val pagerState: PagerState,
        override val calendarItemClickable: Boolean = true,
        override val pagerData: SnapshotStateMap<Int, WeeksData>,
        override val selectedDate: State<LocalDate>,
    ) : CalendarState
}
