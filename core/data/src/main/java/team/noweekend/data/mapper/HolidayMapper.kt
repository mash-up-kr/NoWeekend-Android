package team.noweekend.data.mapper

import team.noweekend.core.model.holiday.Holiday
import team.noweekend.core.remote.model.holiday.HolidayResponse

fun HolidayResponse.toDomain(): List<Holiday> {
    return this.holidays.map {
        Holiday(
            date = it.date,
            holiday = it.holiday,
        )
    }
}
