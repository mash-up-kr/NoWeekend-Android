package team.noweekend.feature.splash.mvi

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import team.noweekend.core.common.android.mvi.SideEffectHandler

@Composable
internal fun rememberSplashSideEffectHandler(
    navigateToLogin: () -> Unit,
    navigateToMain: () -> Unit,
): SplashSideEffectHandler {
    return remember {
        SplashSideEffectHandler(
            navigateToLogin = navigateToLogin,
            navigateToMain = navigateToMain,
        )
    }
}

internal class SplashSideEffectHandler(
    private val navigateToLogin: () -> Unit,
    private val navigateToMain: () -> Unit,
) : SideEffectHandler<SplashSideEffect> {
    override fun handleSideEffect(sideEffect: SplashSideEffect) {
        when (sideEffect) {
            is SplashSideEffect.NavigateToLogin -> navigateToLogin()
            is SplashSideEffect.NavigateToMain -> navigateToMain()
        }
    }
}
