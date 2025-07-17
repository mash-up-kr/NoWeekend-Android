package team.noweekend.core.remote.model.schedule.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ScheduleCreateResponse(
    @SerialName("alarmOption")
    val alarmOption: String,
    @SerialName("category")
    val category: String,
    @SerialName("completed")
    val completed: Boolean,
    @SerialName("endTime")
    val startDateTime: String,
    @SerialName("id")
    val id: String,
    @SerialName("startTime")
    val endDateTime: String,
    @SerialName("temperature")
    val temperature: Int,
    @SerialName("title")
    val title: String,
)
