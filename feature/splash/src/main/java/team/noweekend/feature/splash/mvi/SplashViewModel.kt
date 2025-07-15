package team.noweekend.feature.splash.mvi

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import team.noweekend.core.common.android.base.MVIViewModel
import team.noweekend.core.domain.usecase.GetAccessTokenUseCase
import team.noweekend.core.domain.usecase.GetOnboardFinishedUseCase
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getAccessTokenUseCase: GetAccessTokenUseCase,
    private val getOnboardFinishedUseCase: GetOnboardFinishedUseCase,
) : MVIViewModel<SplashIntent, SplashSideEffect, SplashUiState>(savedStateHandle) {

    init {
        execute {
            delay(1_000)
            getAccessTokenUseCase()
                .onSuccess {
                    getOnboardFinishedUseCase()
                        .onSuccess {
                            if (it) {
                                postSideEffect(SplashSideEffect.NavigateToMain)
                            } else {
                                postSideEffect(SplashSideEffect.NavigateToOnboarding)
                            }
                        }.onFailure {
                            postSideEffect(SplashSideEffect.NavigateToOnboarding)
                        }
                }
                .onFailure { postSideEffect(SplashSideEffect.NavigateToLogin) }
        }
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): SplashUiState {
        return SplashUiState.INITIAL_STATE
    }

    override fun handleClientException(throwable: Throwable) {
        Unit
    }

    override suspend fun handleIntent(intent: SplashIntent) {
        Unit
    }
}
