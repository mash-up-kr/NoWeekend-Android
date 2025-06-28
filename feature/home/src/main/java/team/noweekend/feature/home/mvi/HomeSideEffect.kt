package team.noweekend.feature.home.mvi

import team.noweekend.core.common.android.mvi.SideEffect

sealed interface HomeSideEffect : SideEffect {
    data object NavigateToCreateVacation : HomeSideEffect
}
