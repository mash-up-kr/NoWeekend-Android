package team.noweekend.feature.addtask.mvi

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import team.noweekend.core.common.android.mvi.Intent
import team.noweekend.core.design.system.core.component.toggle.ToggleState
import team.noweekend.core.model.schedule.ScheduleCategory

sealed interface AddTaskIntent : Intent {
    data class SelectTaskType(val taskType: ScheduleCategory) : AddTaskIntent
    data object ClickSave : AddTaskIntent
    data object ClickDetail : AddTaskIntent
    data object ClickBackDetail : AddTaskIntent
    data object ClickBackMain : AddTaskIntent
    data class ToggleAllDay(val toggleState: ToggleState) : AddTaskIntent
    data class SelectStartDate(val date: LocalDate) : AddTaskIntent
    data class SelectStartTime(val date: LocalTime) : AddTaskIntent
    data class SelectEndDate(val date: LocalDate) : AddTaskIntent
    data class SelectEndTime(val date: LocalTime) : AddTaskIntent
    data class WriteTemperature(val temperature: String) : AddTaskIntent
    data class WriteTitle(val title: String) : AddTaskIntent
}
