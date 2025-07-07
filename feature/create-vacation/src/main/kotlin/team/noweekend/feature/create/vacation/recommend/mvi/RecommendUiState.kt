package team.noweekend.feature.create.vacation.recommend.mvi

import androidx.compose.runtime.Stable
import team.noweekend.core.common.android.mvi.UiState

@Stable
data class RecommendUiState(
    val isLoading: Boolean,
) : UiState {
    companion object {
        val INITIAL_STATE: RecommendUiState = RecommendUiState(
            isLoading = false,
        )

        val DUMMY_STATE: RecommendUiState = RecommendUiState(
            isLoading = false,
        )
    }
}
