package team.noweekend.feature.addtask.model

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import team.noweekend.core.common.kotlin.extension.now
import team.noweekend.core.model.schedule.ScheduleCategory
import team.noweekend.feature.addtask.detail.model.VacationTimeType

data class AddTaskInfo(
    val title: String,
    val selectedType: ScheduleCategory,
    val isAllDay: Boolean,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val startTime: LocalTime,
    val endTime: LocalTime,
    val temperature: Int,
    val selectedVacation: VacationTimeType,
) {
    companion object {
        val Empty = AddTaskInfo(
            title = "",
            selectedType = ScheduleCategory.COMPANY,
            isAllDay = false,
            startDate = LocalDate.now(),
            endDate = LocalDate.now(),
            startTime = LocalTime(0, 0),
            endTime = LocalTime(0, 0),
            temperature = 5,
            selectedVacation = VacationTimeType.ALL_DAY,
        )
    }
}
