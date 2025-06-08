package team.noweekend.feature.login.mvi

import androidx.compose.runtime.Stable
import team.noweekend.core.common.android.mvi.UiState
import team.noweekend.feature.login.model.UserAuthInfo

@Stable
data class LoginUiState(
    val isLoading: Boolean,
    val userAuthInfo: UserAuthInfo
) : UiState {
    companion object {
        val INITIAL_STATE: LoginUiState = LoginUiState(
            isLoading = false,
            userAuthInfo = UserAuthInfo(
                authToken = "",
                nickname = ""
            )
        )
    }
}
