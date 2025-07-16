package team.noweekend.feature.calendar.model

import androidx.compose.runtime.Immutable
import kotlinx.collections.immutable.ImmutableList
import team.noweekend.core.common.ui.calendar.model.CalendarDateOfWeek
import team.noweekend.core.common.ui.todo.model.Todo

@Immutable
data class CalendarDateOfWeekWithTodoList(
    val calendarDateOfWeek: CalendarDateOfWeek,
    val todoList: ImmutableList<Todo>,
)
