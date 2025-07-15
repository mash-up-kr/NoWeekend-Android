package team.noweekend.feature.splash.mvi

import team.noweekend.core.common.android.mvi.SideEffect

sealed interface SplashSideEffect : SideEffect {
    data object NavigateToLogin : SplashSideEffect
    data object NavigateToMain : SplashSideEffect
    data object NavigateToOnboarding : SplashSideEffect
}
