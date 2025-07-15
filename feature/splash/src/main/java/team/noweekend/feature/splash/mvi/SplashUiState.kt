package team.noweekend.feature.splash.mvi

import team.noweekend.core.common.android.mvi.UiState

data class SplashUiState(
    val isLoading: Boolean = true,
    val isFirstLaunch: Boolean = false,
) : UiState {
    companion object {
        val INITIAL_STATE = SplashUiState(
            isLoading = true,
            isFirstLaunch = false,
        )
    }
}
