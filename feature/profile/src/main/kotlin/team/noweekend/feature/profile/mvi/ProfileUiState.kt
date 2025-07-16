package team.noweekend.feature.profile.mvi

import androidx.compose.runtime.Stable
import team.noweekend.core.common.android.mvi.UiState

@Stable
data class ProfileUiState(
    val isLoading: Boolean,
) : UiState {
    companion object {
        val INITIAL_STATE: ProfileUiState = ProfileUiState(
            isLoading = false,
        )
    }
}
