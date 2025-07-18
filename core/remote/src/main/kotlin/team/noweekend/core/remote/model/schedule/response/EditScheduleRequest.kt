package team.noweekend.core.remote.model.schedule.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EditScheduleRequest(
    @SerialName("title")
    val title: String,
    @SerialName("startDateTime")
    val startDateTime: String,
    @SerialName("endDateTime")
    val endDateTime: String,
    @SerialName("category")
    val category: String,
    @SerialName("temperature")
    val temperature: Int,
    @SerialName("alarmOption")
    val alarmOption: String,
)
