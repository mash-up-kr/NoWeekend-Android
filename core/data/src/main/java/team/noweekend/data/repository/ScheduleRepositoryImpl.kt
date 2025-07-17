package team.noweekend.data.repository

import team.noweekend.core.domain.repository.ScheduleRepository
import team.noweekend.core.model.schedule.CreateSchedule
import team.noweekend.core.model.schedule.DateWithSchedules
import team.noweekend.core.model.schedule.Schedule
import team.noweekend.core.model.schedule.ScheduleCreateParam
import team.noweekend.core.remote.api.schedule.ScheduleApi
import team.noweekend.data.mapper.toDomain
import team.noweekend.data.mapper.toDomainModel
import team.noweekend.data.mapper.toRequest
import team.noweekend.data.mapper.toSchedule
import javax.inject.Inject

internal class ScheduleRepositoryImpl @Inject constructor(
    private val scheduleApi: ScheduleApi,
) : ScheduleRepository {

    override suspend fun getSchedule(startDate: String, endDate: String): List<DateWithSchedules> {
        return scheduleApi.getSchedule(
            startDate = startDate,
            endDate = endDate,
        ).map { response -> response.toDomain() }
    }

    override suspend fun changeCompleteSchedule(id: String, isComplete: Boolean): Schedule {
        return scheduleApi.changeCompleteSchedule(
            id = id,
            isComplete = isComplete,
        ).toSchedule()
    }

    override suspend fun createSchedule(param: ScheduleCreateParam): CreateSchedule {
        return scheduleApi
            .createSchedule(createScheduleRequest = param.toRequest())
            .toDomainModel()
    }
}
