package team.noweekend.feature.create.vacation.recommend.mvi

import androidx.compose.runtime.Stable
import team.noweekend.core.common.android.mvi.UiState
import team.noweekend.feature.create.vacation.recommend.model.RecommendVacationType
import team.noweekend.feature.create.vacation.recommend.model.RecommendedVacationUiModel

@Stable
data class RecommendUiState(
    val isLoading: Boolean,
    val recommendedVacation: RecommendedVacationUiModel,
) : UiState {
    companion object {
        val INITIAL_STATE: RecommendUiState = RecommendUiState(
            isLoading = false,
            recommendedVacation = RecommendedVacationUiModel.INITIAL_DATA,
        )

        val DUMMY_STATE: RecommendUiState = RecommendUiState(
            isLoading = false,
            recommendedVacation = RecommendedVacationUiModel.INITIAL_DATA
        )
    }
}
