package team.noweekend.feature.login.mvi

import android.content.IntentSender
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import team.noweekend.core.common.android.mvi.SideEffectHandler

@Composable
internal fun rememberLoginSideEffectHandler(
    navigateToOnboarding: () -> Unit,
    navigateToGoogleSignUp: () -> Unit,
    showGoogleSignUpErrorToast: () -> Unit,
    showGoogleLoginSuccessToast: () -> Unit,
    showCancelGoogleAuthToast: () -> Unit,
    showErrorToast: () -> Unit,
    navigateToGoogleAuth: (IntentSender) -> Unit,
): LoginSideEffectHandler {
    return remember {
        LoginSideEffectHandler(
            navigateToOnboarding = navigateToOnboarding,
            navigateToGoogleSignUp = navigateToGoogleSignUp,
            showGoogleSignUpErrorToast = showGoogleSignUpErrorToast,
            navigateToGoogleAuth = navigateToGoogleAuth,
            showGoogleLoginSuccessToast = showGoogleLoginSuccessToast,
            showCancelGoogleAuthToast = showCancelGoogleAuthToast,
            showErrorToast = showErrorToast,
        )
    }
}

internal class LoginSideEffectHandler(
    private val navigateToOnboarding: () -> Unit,
    private val navigateToGoogleSignUp: () -> Unit,
    private val showGoogleSignUpErrorToast: () -> Unit,
    private val showCancelGoogleAuthToast: () -> Unit,
    private val showErrorToast: () -> Unit,
    private val showGoogleLoginSuccessToast: () -> Unit,
    private val navigateToGoogleAuth: (IntentSender) -> Unit,
) : SideEffectHandler<LoginSideEffect> {
    override fun handleSideEffect(sideEffect: LoginSideEffect) {
        when (sideEffect) {
            is LoginSideEffect.NavigateToOnboarding -> navigateToOnboarding()
            is LoginSideEffect.NavigateToGoogleSignUp -> navigateToGoogleSignUp()
            is LoginSideEffect.ShowGoogleLoginErrorToast -> showGoogleSignUpErrorToast()
            is LoginSideEffect.NavigateToGoogleAuth -> navigateToGoogleAuth(sideEffect.intentSender)
            is LoginSideEffect.ShowCancelGoogleAuthToast -> showCancelGoogleAuthToast()
            is LoginSideEffect.ShowErrorToast -> showErrorToast()
            is LoginSideEffect.ShowGoogleLoginSuccessToast -> showGoogleLoginSuccessToast()
        }
    }
}
