package team.noweekend.feature.splash.mvi

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import team.noweekend.core.common.android.base.MVIViewModel
import team.noweekend.core.domain.usecase.GetAccessTokenUseCase
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getAccessTokenUseCase: GetAccessTokenUseCase,
) : MVIViewModel<SplashIntent, SplashSideEffect, SplashUiState>(savedStateHandle) {

    init {
        execute {
            delay(1_000)
            getAccessTokenUseCase()
                .onSuccess { postSideEffect(SplashSideEffect.NavigateToMain) }
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
