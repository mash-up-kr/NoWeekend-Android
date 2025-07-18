package team.noweekend.feature.detail.date.mvi

import team.noweekend.core.common.android.mvi.SideEffect
import team.noweekend.core.common.ui.todo.model.Todo
import team.noweekend.core.model.schedule.Schedule

sealed interface DetailDateSideEffect : SideEffect {
    data class NavigateToAddTodo(val todo: Todo) : DetailDateSideEffect

    data object NavigateToBack : DetailDateSideEffect

    data class NavigateToEditTodo(val schedule: Schedule) : DetailDateSideEffect

    data object NavigateToAddTodoWithDirectInput : DetailDateSideEffect
}
