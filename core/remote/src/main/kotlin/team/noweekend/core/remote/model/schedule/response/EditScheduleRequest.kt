package team.noweekend.core.remote.model.schedule.response

import team.noweekend.core.remote.model.schedule.common.TimeModel

data class EditScheduleRequest(
    val title: String,
    val startTime: TimeModel,
    val endTime: TimeModel,
    val category: String,
    val temperature: Int,
    val allDay: Boolean,
    val alarmOption: String,
)

