package team.noweekend.feature.detail.date.mvi

import team.noweekend.core.common.android.mvi.SideEffect
import team.noweekend.core.common.ui.todo.model.Todo

sealed interface DetailDateSideEffect : SideEffect {
    data class NavigateToAddTodo(val todo: Todo) : DetailDateSideEffect

    data object NavigateToBack : DetailDateSideEffect
}
