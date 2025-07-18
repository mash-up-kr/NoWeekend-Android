package team.noweekend.core.remote.api.schedule

import team.noweekend.core.remote.model.schedule.ScheduleCreateRequest
import team.noweekend.core.remote.model.schedule.common.ScheduleModel
import team.noweekend.core.remote.model.schedule.response.DeleteScheduleResponse
import team.noweekend.core.remote.model.schedule.response.EditScheduleRequest
import team.noweekend.core.remote.model.schedule.response.EditScheduleResponse
import team.noweekend.core.remote.model.schedule.response.GetScheduleResponse
import team.noweekend.core.remote.model.schedule.response.ScheduleCreateResponse

interface ScheduleApi {
    suspend fun getSchedule(
        startDate: String,
        endDate: String,
    ): List<GetScheduleResponse>

    suspend fun editSchedule(
        id: String,
        editScheduleRequest: EditScheduleRequest,
    ): EditScheduleResponse

    suspend fun deleteSchedule(
        id: String,
    ): DeleteScheduleResponse

    suspend fun createSchedule(
        createScheduleRequest: ScheduleCreateRequest,
    ): ScheduleCreateResponse

    suspend fun changeCompleteSchedule(
        id: String,
        isComplete: Boolean,
    ): ScheduleModel

    companion object {
        const val SCHEDULE_PATH: String = "/api/v1/schedule"
        const val START_DATE: String = "start_date"
        const val END_DATE: String = "end_date"
    }
}
