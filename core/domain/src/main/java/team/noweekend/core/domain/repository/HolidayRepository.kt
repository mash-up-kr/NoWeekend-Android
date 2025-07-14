package team.noweekend.core.domain.repository

import team.noweekend.core.model.holiday.Holiday

interface HolidayRepository {
    suspend fun getHoliday(year: Int, month: Int): List<Holiday>
    suspend fun getRemainedHoliday(): List<Holiday>
}
