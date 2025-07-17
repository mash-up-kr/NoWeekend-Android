package team.noweekend.core.remote.api.schedule

import io.ktor.client.HttpClient
import team.noweekend.core.remote.api.schedule.ScheduleApi.Companion.END_DATE
import team.noweekend.core.remote.api.schedule.ScheduleApi.Companion.START_DATE
import team.noweekend.core.remote.base.deleteApiCall
import team.noweekend.core.remote.base.getApiCall
import team.noweekend.core.remote.base.postApiCall
import team.noweekend.core.remote.base.putApiCall
import team.noweekend.core.remote.model.schedule.ScheduleCreateRequest
import team.noweekend.core.remote.model.schedule.common.ScheduleModel
import team.noweekend.core.remote.model.schedule.response.DeleteScheduleResponse
import team.noweekend.core.remote.model.schedule.response.EditScheduleRequest
import team.noweekend.core.remote.model.schedule.response.GetScheduleResponse
import team.noweekend.core.remote.model.schedule.response.ScheduleCreateResponse
import team.noweekend.core.remote.qualifier.BasicClient
import javax.inject.Inject

class ScheduleApiImpl @Inject constructor(
    @BasicClient val client: HttpClient,
) : ScheduleApi {
    override suspend fun getSchedule(startDate: String, endDate: String): List<GetScheduleResponse> {
        return client.getApiCall(
            path = ScheduleApi.SCHEDULE_PATH,
            queries = mapOf(
                START_DATE to startDate,
                END_DATE to endDate,
            ),
        )
    }

    override suspend fun editSchedule(
        id: String,
        editScheduleRequest: EditScheduleRequest,
    ): ScheduleModel {
        return client.putApiCall(
            path = ScheduleApi.SCHEDULE_PATH + "/${id}",
            body = editScheduleRequest,
        )
    }

    override suspend fun deleteSchedule(id: String): DeleteScheduleResponse {
        return client.deleteApiCall(
            path = ScheduleApi.SCHEDULE_PATH + "/${id}",
        )
    }

    override suspend fun createSchedule(createScheduleRequest: ScheduleCreateRequest): ScheduleCreateResponse {
        return client.postApiCall(
            path = "/api/v2/schedule",
            body = createScheduleRequest,
        )
    }

    override suspend fun changeCompleteSchedule(id: String, isComplete: Boolean): ScheduleModel {
        return client.putApiCall(
            path = ScheduleApi.SCHEDULE_PATH + "/${id}/state",
            body = null,
            queries = mapOf("is_complete" to isComplete),
        )
    }
}
