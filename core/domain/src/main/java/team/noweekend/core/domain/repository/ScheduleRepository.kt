package team.noweekend.core.domain.repository

import team.noweekend.core.model.schedule.CreateSchedule
import team.noweekend.core.model.schedule.DateWithSchedules
import team.noweekend.core.model.schedule.Schedule
import team.noweekend.core.model.schedule.ScheduleCreateParam

interface ScheduleRepository {
    suspend fun getSchedule(
        startDate: String,
        endDate: String,
    ): List<DateWithSchedules>

    suspend fun changeCompleteSchedule(
        id: String,
        isComplete: Boolean,
    ): Schedule

    suspend fun createSchedule(param: ScheduleCreateParam): CreateSchedule

    suspend fun deleteSchedule(id: String): String
}
