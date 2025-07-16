package team.noweekend.core.remote.model.schedule.response

import kotlinx.serialization.Serializable
import team.noweekend.core.remote.model.schedule.common.ScheduleModel

@Serializable
data class GetScheduleResponse(
    val date: String,
    val dailyTemperature: Int,
    val schedules: List<ScheduleModel>
)
