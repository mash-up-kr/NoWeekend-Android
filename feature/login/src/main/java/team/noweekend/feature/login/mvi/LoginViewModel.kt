package team.noweekend.feature.login.mvi

import androidx.credentials.exceptions.NoCredentialException
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import team.noweekend.core.common.android.base.MVIViewModel
import team.noweekend.feature.login.manager.GoogleLoginManager
import team.noweekend.feature.login.model.UserAuthInfo
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val googleLoginManager: GoogleLoginManager
) : MVIViewModel<LoginIntent, LoginSideEffect, LoginUiState>(savedStateHandle) {

    override fun createInitialState(savedStateHandle: SavedStateHandle): LoginUiState {
        return LoginUiState.INITIAL_STATE
    }

    override fun handleClientException(throwable: Throwable) {
        Unit
    }

    override suspend fun handleIntent(intent: LoginIntent) {
        when (intent) {
            LoginIntent.ClickGoogleLogin -> {
                googleLogin()
            }
        }
    }

    private fun googleLogin() {
        viewModelScope.launch {
            googleLoginManager.login()
                .onSuccess {
                    updateUserAuthInfo(it)
                    postSideEffect(LoginSideEffect.NavigateToOnboarding)
                }
                .onFailure { exception ->
                    if (exception is NoCredentialException) {
                        postSideEffect(LoginSideEffect.NavigateToGoogleSignUp)
                    } else {
                        postSideEffect(LoginSideEffect.ShowGoogleLoginErrorToast)
                    }
                }
        }
    }

    private fun updateUserAuthInfo(userAuthInfo: UserAuthInfo) {
        reduce {
            copy(
                userAuthInfo = userAuthInfo
            )
        }
    }
}
