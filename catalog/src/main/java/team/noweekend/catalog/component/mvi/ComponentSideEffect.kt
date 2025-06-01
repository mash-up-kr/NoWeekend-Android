package team.noweekend.catalog.component.mvi

import team.noweekend.core.common.android.mvi.SideEffect

sealed interface ComponentSideEffect : SideEffect {
    data object NavigateToHistoryBack : ComponentSideEffect
}
