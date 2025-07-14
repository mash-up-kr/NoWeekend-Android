package team.noweekend.core.model.calendar

import kotlinx.datetime.LocalDate

data class DateOfWeek(
    val imageType: ImageType,
    val localDate: LocalDate,
    val isCurrentDate: Boolean,
)
