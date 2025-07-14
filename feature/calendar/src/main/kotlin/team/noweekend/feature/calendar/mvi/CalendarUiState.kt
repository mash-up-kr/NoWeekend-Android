package team.noweekend.feature.calendar.mvi

import androidx.compose.runtime.Immutable
import kotlinx.collections.immutable.ImmutableMap
import kotlinx.collections.immutable.persistentMapOf
import kotlinx.datetime.LocalDate
import team.noweekend.core.common.android.mvi.UiState
import team.noweekend.core.common.kotlin.extension.now
import team.noweekend.core.common.ui.calendar.model.CalendarMode
import team.noweekend.core.common.ui.calendar.model.CalendarWeeksData


@Immutable
data class CalendarUiState(
    val chooserMonth: LocalDate,
    val selectedDate: LocalDate,
    val calendarMode: CalendarMode,
    val calendarWeeksData : ImmutableMap<Int, CalendarWeeksData>,
    val calendarMonthsData : ImmutableMap<Int, CalendarWeeksData>
) : UiState {
    companion object {
        val default = CalendarUiState(
            chooserMonth = LocalDate.now(),
            selectedDate = LocalDate.now(),
            calendarWeeksData = persistentMapOf(),
            calendarMonthsData = persistentMapOf(),
            calendarMode = CalendarMode.WEEK
        )
    }
}
