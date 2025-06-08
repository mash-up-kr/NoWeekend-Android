package team.noweekend.feature.login.mvi

import team.noweekend.core.common.android.mvi.SideEffect

sealed interface LoginSideEffect : SideEffect {
    data object NavigateToOnboarding : LoginSideEffect
    data object NavigateToGoogleSignUp : LoginSideEffect
    data object ShowGoogleLoginErrorToast : LoginSideEffect
}
