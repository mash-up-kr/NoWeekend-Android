package team.noweekend.core.domain.repository

import team.noweekend.core.model.schedule.DateWithSchedules
import team.noweekend.core.model.schedule.Schedule

interface ScheduleRepository {
    suspend fun getSchedule(
        startDate: String,
        endDate: String,
    ): List<DateWithSchedules>

    suspend fun changeCompleteSchedule(
        id: String,
        isComplete: Boolean
    ) : Schedule
}
