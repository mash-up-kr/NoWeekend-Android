package team.noweekend.feature.login.screen

import android.app.Activity
import android.content.Context
import androidx.activity.compose.LocalActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.android.gms.auth.api.identity.Identity
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
    showGoogleLoginSuccessToast: () -> Unit,
    showCancelGoogleAuthToast: () -> Unit,
    showErrorToast: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val context = LocalActivity.current ?: LocalContext.current
    val loginLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult()
    ) { result ->
        handleActivityResult(result, context, viewModel)
    }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val sideEffectHandler: LoginSideEffectHandler = rememberLoginSideEffectHandler(
        navigateToOnboarding = navigateToOnboarding,
        navigateToGoogleSignUp = navigateToGoogleSignUp,
        showGoogleSignUpErrorToast = showGoogleSignUpErrorToast,
        navigateToGoogleAuth = { intentSender ->
            loginLauncher.launch(
                IntentSenderRequest.Builder(intentSender).build()
            )
        },
        showGoogleLoginSuccessToast = showGoogleLoginSuccessToast,
        showCancelGoogleAuthToast = showCancelGoogleAuthToast,
        showErrorToast = showErrorToast
    )

    LaunchedEffect(key1 = Unit) {
        viewModel.sideEffect.collectLatest { sideEffect ->
            sideEffectHandler.handleSideEffect(sideEffect)
        }
    }

    LoginScreen(
        modifier = modifier,
        uiState = uiState,
        onClickGoogleLogin = { viewModel.intent(LoginIntent.ClickGoogleLogin(context)) }
    )
}

private fun handleActivityResult(
    result: ActivityResult,
    context: Context,
    viewModel: LoginViewModel
) {
    when (result.resultCode) {
        Activity.RESULT_OK -> {
            val authResult = Identity.getAuthorizationClient(context)
                .getAuthorizationResultFromIntent(result.data)
            viewModel.intent(LoginIntent.ClickGoogleAuthLogin(authResult.serverAuthCode))
        }

        Activity.RESULT_CANCELED -> {
            viewModel.intent(LoginIntent.ClickCancelLogin)
        }
    }
}
