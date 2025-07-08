package team.noweekend.core.model.schedule

import team.noweekend.core.model.alarm.AlarmOption

data class Schedule(
    val id: String,
    val title: String,
    val startTime: String,
    val endTime: String,
    val category: ScheduleCategory,
    val temperature: Int,
    val allDay: Boolean,
    val completed: Boolean,
    val alarmOption: AlarmOption,
)
