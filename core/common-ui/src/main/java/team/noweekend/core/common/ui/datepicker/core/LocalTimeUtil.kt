package team.noweekend.core.common.ui.datepicker.core

import kotlinx.datetime.LocalTime
import team.noweekend.core.common.ui.datepicker.model.Meridiem

internal object LocalTimeUtil {
    fun convertToLocalTime(meridiem: Meridiem, hour: Int, minute: Int): LocalTime {
        require(hour in 1..12) { "Hour must be between 1 and 12" }
        require(minute in 0..59) { "Minute must be between 0 and 59" }
        require(meridiem in Meridiem.entries.toTypedArray()) { "meridiem must be AM or PM" }

        val convertedHour = when {
            meridiem == Meridiem.AM && hour == 12 -> 0
            meridiem == Meridiem.AM -> hour
            meridiem == Meridiem.PM && hour == 12 -> 12
            meridiem == Meridiem.PM -> hour + 12
            else -> error("Invalid time format")
        }

        return LocalTime(
            hour = convertedHour,
            minute = minute,
        )
    }
}
