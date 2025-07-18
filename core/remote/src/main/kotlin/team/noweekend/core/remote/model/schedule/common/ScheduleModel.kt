package team.noweekend.core.remote.model.schedule.common

import kotlinx.serialization.Serializable

@Serializable
data class ScheduleModel(
    val id: String,
    val title: String,
    val startTime: String,
    val endTime: String,
    val category: String,
    val temperature: Int,
    val allDay: Boolean,
    val alarmOption: String,
    val completed: Boolean,
)


