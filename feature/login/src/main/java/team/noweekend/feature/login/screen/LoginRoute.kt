package team.noweekend.feature.login.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest
import team.noweekend.feature.login.mvi.LoginIntent
import team.noweekend.feature.login.mvi.LoginSideEffectHandler
import team.noweekend.feature.login.mvi.LoginViewModel
import team.noweekend.feature.login.mvi.rememberLoginSideEffectHandler

@Composable
internal fun LoginRoute(
    navigateToOnboarding: () -> Unit,
    navigateToGoogleSignUp: () -> Unit,
    showGoogleSignUpErrorToast: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val sideEffectHandler: LoginSideEffectHandler = rememberLoginSideEffectHandler(
        navigateToOnboarding = navigateToOnboarding,
        navigateToGoogleSignUp = navigateToGoogleSignUp,
        showGoogleSignUpErrorToast = showGoogleSignUpErrorToast
    )

    LaunchedEffect(key1 = Unit) {
        viewModel.sideEffect.collectLatest { sideEffect ->
            sideEffectHandler.handleSideEffect(sideEffect)
        }
    }

    LoginScreen(
        modifier = modifier,
        uiState = uiState,
        onClickGoogleLogin = { viewModel.intent(LoginIntent.ClickGoogleLogin) }
    )
}
