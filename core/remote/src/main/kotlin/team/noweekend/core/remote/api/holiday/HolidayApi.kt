package team.noweekend.core.remote.api.holiday

import team.noweekend.core.remote.model.holiday.HolidayResponse

interface HolidayApi {
    suspend fun getHoliday(year: Int, month: Int): HolidayResponse
    suspend fun getRemainedHoliday(): HolidayResponse
}
