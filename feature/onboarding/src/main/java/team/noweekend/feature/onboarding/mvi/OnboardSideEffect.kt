package team.noweekend.feature.onboarding.mvi

import team.noweekend.core.common.android.mvi.SideEffect

sealed interface OnboardSideEffect : SideEffect {
    data object NavigateToVacation : OnboardSideEffect
    data object NavigateToScheduleTag : OnboardSideEffect
    data object NavigateToHome : OnboardSideEffect
    data object NavigateToBack : OnboardSideEffect
}
