package team.noweekend.core.common.ui.calendar.model

import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Stable
import kotlinx.collections.immutable.ImmutableMap
import kotlinx.collections.immutable.persistentMapOf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.datetime.LocalDate
import team.noweekend.core.common.kotlin.extension.now

@Stable
sealed interface CalendarState {
    val mode: CalendarMode
    val pagerState: PagerState
    val calendarItemClickable: Boolean
    val pagerData: StateFlow<ImmutableMap<Int, CalendarWeeksData>>
    val selectedDate: LocalDate

    @Stable
    data class Week(
        override val mode: CalendarMode = CalendarMode.WEEK,
        override val pagerState: PagerState,
        override val calendarItemClickable: Boolean = true,
        override val pagerData: StateFlow<ImmutableMap<Int, CalendarWeeksData>>,
        override val selectedDate: LocalDate,
    ) : CalendarState {
        companion object {
            val default = Week(
                pagerState = PagerState() { 0 },
                selectedDate = LocalDate.now(),
                pagerData = MutableStateFlow(persistentMapOf()),
            )
        }
    }

    @Stable
    data class Month(
        override val mode: CalendarMode = CalendarMode.MONTH,
        override val pagerState: PagerState,
        override val calendarItemClickable: Boolean = true,
        override val pagerData: StateFlow<ImmutableMap<Int, CalendarWeeksData>>,
        override val selectedDate: LocalDate,
    ) : CalendarState
}
