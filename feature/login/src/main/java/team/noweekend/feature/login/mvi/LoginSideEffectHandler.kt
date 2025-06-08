package team.noweekend.feature.login.mvi

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import team.noweekend.core.common.android.mvi.SideEffect
import team.noweekend.core.common.android.mvi.SideEffectHandler

@Composable
internal fun rememberLoginSideEffectHandler(
    navigateToOnboarding: () -> Unit,
    navigateToGoogleSignUp: () -> Unit,
    showGoogleSignUpErrorToast: () -> Unit
): LoginSideEffectHandler {
    return remember {
        LoginSideEffectHandler(
            navigateToOnboarding = navigateToOnboarding,
            navigateToGoogleSignUp = navigateToGoogleSignUp,
            showGoogleSignUpErrorToast = showGoogleSignUpErrorToast
        )
    }
}

internal class LoginSideEffectHandler(
    private val navigateToOnboarding: () -> Unit,
    private val navigateToGoogleSignUp: () -> Unit,
    private val showGoogleSignUpErrorToast: () -> Unit
) : SideEffectHandler<SideEffect> {
    override fun handleSideEffect(sideEffect: SideEffect) {
        when (sideEffect) {
            is LoginSideEffect.NavigateToOnboarding -> navigateToOnboarding()
            is LoginSideEffect.NavigateToGoogleSignUp -> navigateToGoogleSignUp()
            is LoginSideEffect.ShowGoogleLoginErrorToast -> showGoogleSignUpErrorToast()
        }
    }
}
