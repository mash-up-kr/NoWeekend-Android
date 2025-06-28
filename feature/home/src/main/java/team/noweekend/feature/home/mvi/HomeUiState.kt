package team.noweekend.feature.home.mvi

import team.noweekend.core.common.android.mvi.UiState

data class HomeUiState(
    val isLoading: Boolean,
) : UiState {

    companion object {
        val INITIAL_STATE: HomeUiState = HomeUiState(
            isLoading = false,
        )
    }
}
