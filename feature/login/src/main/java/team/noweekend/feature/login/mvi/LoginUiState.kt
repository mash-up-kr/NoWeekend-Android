package team.noweekend.feature.login.mvi

import androidx.compose.runtime.Stable
import team.noweekend.core.common.android.mvi.UiState

@Stable
data class LoginUiState(
    val isLoading: Boolean,
) : UiState {
    companion object {
        val INITIAL_STATE: LoginUiState = LoginUiState(
            isLoading = false,
        )
    }
}
