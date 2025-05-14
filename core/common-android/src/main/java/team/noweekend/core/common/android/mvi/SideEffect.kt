package team.noweekend.core.common.android.mvi

sealed interface SideEffect {
    data object NavigateToHistoryBack : SideEffect
}