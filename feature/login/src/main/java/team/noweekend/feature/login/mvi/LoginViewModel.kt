package team.noweekend.feature.login.mvi

import android.content.Context
import androidx.credentials.exceptions.GetCredentialCancellationException
import androidx.credentials.exceptions.NoCredentialException
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import team.noweekend.core.common.android.base.MVIViewModel
import team.noweekend.core.domain.usecase.LoginUseCase
import team.noweekend.core.domain.usecase.SaveAccessTokenUseCase
import team.noweekend.feature.login.manager.GoogleAuthManager
import team.noweekend.feature.login.model.UserLoginInfo
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val googleAuthManager: GoogleAuthManager,
    private val loginUseCase: LoginUseCase,
    private val saveAccessTokenUseCase: SaveAccessTokenUseCase,
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
                requestLogin(userName = uiState.value.userName, authCode = intent.authCode ?: "")
            }
        }
    }

    private fun startGoogleLogin(context: Context) {
        viewModelScope.launch {
            googleAuthManager.startGoogleLogin(context)
                .catch { exception ->
                    handleGoogleLoginException(exception)
                }
                .collectLatest {
                    val (userName, authResult) = it.first to it.second
                    fetchUserName(userName = userName)
                    if (authResult.hasResolution()) {
                        authResult.pendingIntent?.intentSender?.let { intentSender ->
                            postSideEffect(LoginSideEffect.NavigateToGoogleAuth(intentSender))
                        }
                    } else {
                        requestLogin(userName = userName, authCode = authResult.serverAuthCode ?: "")
                    }
                }
        }
    }

    private fun fetchUserName(userName: String) {
        reduce {
            copy(
                userName = userName,
            )
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

    private fun requestLogin(userName: String, authCode: String) {
        val requestModel = UserLoginInfo(userName, authCode).toLoginRequest()
        execute {
            loginUseCase(requestModel)
                .onSuccess {
                    saveUserAccessToken(it.accessToken)
                    postSideEffect(LoginSideEffect.ShowGoogleLoginSuccessToast)
                    postSideEffect(LoginSideEffect.NavigateToOnboarding)
                }
                .onFailure {
                    postSideEffect(LoginSideEffect.ShowErrorToast)
                }
        }
    }

    private fun saveUserAccessToken(accessToken: String) {
        execute {
            saveAccessTokenUseCase(accessToken)
        }
    }
}
