package team.noweekend.core.model.schedule

data class CreateSchedule(
    val alarmOption: String,
    val category: String,
    val completed: Boolean,
    val startDateTime: String,
    val id: String,
    val endDateTime: String,
    val temperature: Int,
    val title: String,
)
