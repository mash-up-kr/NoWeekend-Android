package team.noweekend.core.remote.model.schedule.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EditScheduleResponse(
    @SerialName("id")
    val id: String,
    @SerialName("title")
    val title: String,
    @SerialName("startTime")
    val startDateTime: String,
    @SerialName("endTime")
    val endDateTime: String,
    @SerialName("category")
    val category: String,
    @SerialName("temperature")
    val temperature: Int,
    @SerialName("alarmOption")
    val alarmOption: String,
    @SerialName("completed")
    val completed: Boolean,
)
