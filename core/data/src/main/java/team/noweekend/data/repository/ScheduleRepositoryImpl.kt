package team.noweekend.data.repository

import team.noweekend.core.domain.repository.ScheduleRepository
import team.noweekend.core.model.schedule.DateWithSchedules
import team.noweekend.core.remote.api.schedule.ScheduleApi
import team.noweekend.data.mapper.toDomain
import javax.inject.Inject

class ScheduleRepositoryImpl @Inject constructor(
    private val scheduleApi: ScheduleApi,
) : ScheduleRepository {

    override suspend fun getSchedule(startDate: String, endDate: String): List<DateWithSchedules> {
        return scheduleApi.getSchedule(
            startDate = startDate,
            endDate = endDate,
        ).map { response -> response.toDomain() }
    }
}
