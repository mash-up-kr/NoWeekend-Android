package team.noweekend.feature.login.mvi

import android.content.IntentSender
import team.noweekend.core.common.android.mvi.SideEffect

sealed interface LoginSideEffect : SideEffect {
    data object NavigateToOnboarding : LoginSideEffect
    data object NavigateToGoogleSignUp : LoginSideEffect
    data object ShowGoogleLoginErrorToast : LoginSideEffect
    data object ShowGoogleLoginSuccessToast : LoginSideEffect
    data object ShowCancelGoogleAuthToast : LoginSideEffect
    data object ShowErrorToast : LoginSideEffect
    data class NavigateToGoogleAuth(val intentSender: IntentSender) : LoginSideEffect
}
