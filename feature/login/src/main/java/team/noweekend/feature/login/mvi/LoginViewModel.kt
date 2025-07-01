package team.noweekend.feature.login.mvi

import android.content.Context
import android.util.Log
import androidx.credentials.exceptions.GetCredentialCancellationException
import androidx.credentials.exceptions.NoCredentialException
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import team.noweekend.core.common.android.base.MVIViewModel
import team.noweekend.feature.login.manager.GoogleAuthManager
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val googleAuthManager: GoogleAuthManager,
) : MVIViewModel<LoginIntent, LoginSideEffect, LoginUiState>(savedStateHandle) {

    override fun createInitialState(savedStateHandle: SavedStateHandle): LoginUiState {
        return LoginUiState.INITIAL_STATE
    }

    override fun handleClientException(throwable: Throwable) {
        Unit
    }

    override suspend fun handleIntent(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.ClickGoogleLogin -> {
                startGoogleLogin(intent.context)
            }

            LoginIntent.ClickCancelLogin -> {
                postSideEffect(LoginSideEffect.ShowCancelGoogleAuthToast)
            }

            is LoginIntent.ClickGoogleAuthLogin -> {
                Log.d("LoginViewModel", "auth code : ${intent.authCode}")
                postSideEffect(LoginSideEffect.ShowGoogleLoginSuccessToast)
                // TODO : 서버로 auth code 전달하는 로직 구현
            }
        }
    }

    private fun startGoogleLogin(context: Context) {
        viewModelScope.launch {
            googleAuthManager.startGoogleLogin(context)
                .catch { exception ->
                    handleGoogleLoginException(exception)
                }
                .collectLatest { authResult ->
                    if (authResult.hasResolution()) {
                        authResult.pendingIntent?.intentSender?.let { intentSender ->
                            postSideEffect(LoginSideEffect.NavigateToGoogleAuth(intentSender))
                        }
                    } else {
                        // TODO : authCode 서버로 보내는 api 연결
                        Log.d("LoginViewModel", "Auth Code: ${authResult.serverAuthCode}")
                        postSideEffect(LoginSideEffect.ShowGoogleLoginSuccessToast)
                        postSideEffect(LoginSideEffect.NavigateToOnboarding)
                    }
                }
        }
    }

    private suspend fun handleGoogleLoginException(exception: Throwable) {
        when (exception) {
            is GetCredentialCancellationException -> {
                postSideEffect(LoginSideEffect.ShowCancelGoogleAuthToast)
            }

            is NoCredentialException -> {
                postSideEffect(LoginSideEffect.ShowGoogleLoginErrorToast)
                postSideEffect(LoginSideEffect.NavigateToGoogleSignUp)
            }

            else -> {
                postSideEffect(LoginSideEffect.ShowGoogleLoginErrorToast)
            }
        }
    }
}
