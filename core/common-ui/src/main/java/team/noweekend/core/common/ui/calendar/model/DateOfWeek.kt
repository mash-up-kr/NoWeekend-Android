package team.noweekend.core.common.ui.calendar.model

import androidx.compose.runtime.Immutable
import kotlinx.datetime.LocalDate
import team.noweekend.core.common.ui.calendar.CalendarDataProvider.ImageType

@Immutable
data class DateOfWeek(
    val imageType: ImageType,
    val localDate: LocalDate,
)
