package team.noweekend.feature.splash.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest
import team.noweekend.feature.splash.mvi.SplashSideEffectHandler
import team.noweekend.feature.splash.mvi.SplashViewModel
import team.noweekend.feature.splash.mvi.rememberSplashSideEffectHandler

@Composable
fun SplashRoute(
    navigateToLogin: () -> Unit,
    navigateToMain: () -> Unit,
    navigateToOnboarding: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SplashViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val sideEffectHandler: SplashSideEffectHandler = rememberSplashSideEffectHandler(
        navigateToLogin = navigateToLogin,
        navigateToMain = navigateToMain,
        navigateToOnboarding = navigateToOnboarding,
    )

    LaunchedEffect(key1 = Unit) {
        viewModel.sideEffect.collectLatest { sideEffect ->
            sideEffectHandler.handleSideEffect(sideEffect)
        }
    }

    SplashScreen(
        modifier = modifier.fillMaxSize(),
    )
}
