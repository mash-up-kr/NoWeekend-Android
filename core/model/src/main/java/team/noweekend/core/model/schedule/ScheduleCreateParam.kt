package team.noweekend.core.model.schedule

data class ScheduleCreateParam(
    val title: String,
    val startDateTime: String,
    val endDateTime: String,
    val category: String,
    val temperature: Int,
    val alarmOption: String,
)
