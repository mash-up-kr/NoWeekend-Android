package team.noweekend.core.domain.repository

import team.noweekend.core.model.schedule.DateWithSchedules

interface ScheduleRepository {
    suspend fun getSchedule(
        startDate: String,
        endDate: String,
    ): List<DateWithSchedules>
}
