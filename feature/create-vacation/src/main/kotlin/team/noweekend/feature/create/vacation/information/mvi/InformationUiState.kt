package team.noweekend.feature.create.vacation.information.mvi

import androidx.compose.runtime.Stable
import team.noweekend.core.common.android.mvi.UiState

@Stable
data class InformationUiState(
    val isLoading: Boolean,
) : UiState {
    val isButtonEnabled: Boolean = false

    companion object {
        val INITIAL_STATE: InformationUiState = InformationUiState(
            isLoading = false,
        )

        val DUMMY_STATE: InformationUiState = InformationUiState(
            isLoading = false,
        )
    }
}
