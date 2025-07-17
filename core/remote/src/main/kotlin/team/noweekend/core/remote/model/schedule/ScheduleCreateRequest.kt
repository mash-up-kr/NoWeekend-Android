package team.noweekend.core.remote.model.schedule

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ScheduleCreateRequest(
    @SerialName("title")
    val title: String,
    @SerialName("date")
    val startDateTime: String,
    @SerialName("startTime")
    val endDateTime: String,
    @SerialName("category")
    val category: String,
    @SerialName("temperature")
    val temperature: Int,
    @SerialName("alarmOption")
    val alarmOption: String,
)
