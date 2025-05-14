package team.noweekend.feature.sample.home.mvi

import team.noweekend.core.common.android.mvi.UiState

data class SampleUiState(
    val isLoading: Boolean,
) : UiState {
    companion object {
        val INITIAL_STATE: SampleUiState = SampleUiState(
            isLoading = true,
        )
    }
}
