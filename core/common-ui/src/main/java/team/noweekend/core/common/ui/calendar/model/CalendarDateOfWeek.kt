package team.noweekend.core.common.ui.calendar.model

import androidx.compose.runtime.Immutable
import kotlinx.datetime.LocalDate


@Immutable
data class CalendarDateOfWeek(
    val calendarImageType: CalendarImageType,
    val localDate: LocalDate,
    val isCurrentDate: Boolean,
)
