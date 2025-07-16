package team.noweekend.feature.calendar.model.mapper

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import team.noweekend.core.common.kotlin.extension.MERIDIEM_HOUR_MINUTE_KR_PATTERN
import team.noweekend.core.common.kotlin.extension.MONTH_DATE_WITH_DAY_OF_WEEK_KR_PATTERN
import team.noweekend.core.common.kotlin.extension.toFormattedString
import team.noweekend.core.common.ui.todo.model.Todo
import team.noweekend.core.common.ui.todo.model.TodoType
import team.noweekend.core.model.schedule.Schedule
import team.noweekend.core.model.schedule.ScheduleCategory

fun Schedule.toTodo(): Todo {
    return Todo(
        title = this.title,
        description = getDescription(allDay = this.allDay, startTime = this.startTime),
        todoType = this.category.toTodoType(),
        isDone = this.completed,
        id = this.id
    )
}

fun ScheduleCategory.toTodoType(): TodoType {
    return when (this) {
        ScheduleCategory.ETC -> TodoType.Etc()
        ScheduleCategory.LEAVE -> TodoType.AnnualLeave()
        ScheduleCategory.COMPANY -> TodoType.Company()
        ScheduleCategory.PERSONAL -> TodoType.Personal()
    }
}

fun getDescription(allDay: Boolean, startTime: String): String {
    val localDateTime = LocalDateTime.parse(startTime)
    return if (allDay) {
        localDateTime.toFormattedString(
            pattern = LocalDate.MONTH_DATE_WITH_DAY_OF_WEEK_KR_PATTERN,
        )
    } else {
        localDateTime.toFormattedString(pattern = LocalTime.MERIDIEM_HOUR_MINUTE_KR_PATTERN)
    }
}

