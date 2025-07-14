package team.noweekend.data.repository

import team.noweekend.core.domain.repository.HolidayRepository
import team.noweekend.core.model.holiday.Holiday
import team.noweekend.core.remote.api.holiday.HolidayApi
import team.noweekend.data.mapper.toDomain
import javax.inject.Inject

class HolidayRepositoryImpl @Inject constructor(
    private val holidayApi: HolidayApi,
) : HolidayRepository {
    override suspend fun getHoliday(year: Int, month: Int): List<Holiday> {
        return holidayApi.getHoliday(year = year, month = month).toDomain()
    }

    override suspend fun getRemainedHoliday(): List<Holiday> {
        return holidayApi.getRemainedHoliday().toDomain()
    }
}
