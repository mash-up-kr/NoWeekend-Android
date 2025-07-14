package team.noweekend.feature.create.vacation.information.mvi

import team.noweekend.core.common.android.mvi.SideEffect

sealed interface InformationSideEffect : SideEffect {
    data object NavigateToHistoryBack : InformationSideEffect
    data object NavigateToHome : InformationSideEffect
}
