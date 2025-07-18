package team.noweekend.core.model.calendar

import kotlinx.datetime.LocalDate
import team.noweekend.core.model.schedule.Schedule
import team.noweekend.core.model.schedule.ScheduleCategory

enum class ImageType {
    NONE,
    FUTURE_SCHEDULE,
    BURN_OUT,
    REST,
    OVER_ZERO_UNDER_FIFTY_DEGREE,
    OVER_FIFTY_UNDER_SEVENTY_FIVE_DEGREE,

    ;
}

fun getImageType(temperature: Int, isFuture: Boolean, hasRest: Boolean, hasSchedule: Boolean): ImageType {
    return when {
        hasRest -> ImageType.REST
        temperature in 1..49 -> ImageType.OVER_ZERO_UNDER_FIFTY_DEGREE
        temperature in 50..74 -> ImageType.OVER_FIFTY_UNDER_SEVENTY_FIVE_DEGREE
        temperature >= 75 -> ImageType.BURN_OUT
        hasSchedule -> ImageType.NONE
        isFuture -> ImageType.FUTURE_SCHEDULE
        else -> ImageType.FUTURE_SCHEDULE
    }
}

fun List<Schedule>.getImageType(dateOfWeek: DateOfWeek, localDate: LocalDate): ImageType {
    val temperature = this.filter { it.completed }.sumOf { it.temperature }
    val isFuture = dateOfWeek.localDate > localDate
    val hasRest = this.any { it.category == ScheduleCategory.LEAVE }
    return getImageType(
        temperature = temperature,
        isFuture = isFuture,
        hasRest = hasRest,
        hasSchedule = this.isNotEmpty(),
    )
}
