package team.noweekend.feature.addtask.mvi

import team.noweekend.core.common.android.mvi.SideEffect

sealed interface AddTaskSideEffect : SideEffect {
    data object NavigateToBack : AddTaskSideEffect
    data object NavigateToDetail : AddTaskSideEffect
    data object NavigateToCalendar : AddTaskSideEffect
    data object ShowSuccessToast : AddTaskSideEffect
    data object ShowErrorToast : AddTaskSideEffect
}
