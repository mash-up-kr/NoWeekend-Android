package team.noweekend.core.model.calendar

import kotlinx.datetime.LocalDate
import team.noweekend.core.model.schedule.Schedule

data class DateOfWeek(
    val imageType: ImageType,
    val localDate: LocalDate,
    val isCurrentDate: Boolean,
    val scheduleList: List<Schedule>,
)
