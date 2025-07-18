package team.noweekend.feature.calendar.mvi

import team.noweekend.core.common.android.mvi.SideEffect
import team.noweekend.core.common.ui.todo.model.Todo
import team.noweekend.core.model.schedule.Schedule

sealed interface CalendarSideEffect : SideEffect {
    data object CompleteInitMonthCalendar : CalendarSideEffect
    data object CompleteInitWeekCalendar : CalendarSideEffect

    data object CollectWeekPagerStatePage : CalendarSideEffect
    data object CollectMonthPagerStatePage : CalendarSideEffect
    data class UpdateWeekCalendarPage(val currentPage: Int) : CalendarSideEffect
    data class UpdateMonthCalendarPage(val currentPage: Int) : CalendarSideEffect

    data class NavigateToDetailDate(val date: String) : CalendarSideEffect

    data class NavigateToAddTodo(val todo: Todo) : CalendarSideEffect

    data object NavigateToAddTodoWithDirectInput : CalendarSideEffect

    data class NavigateToEditTodo(val schedule: Schedule): CalendarSideEffect


}
