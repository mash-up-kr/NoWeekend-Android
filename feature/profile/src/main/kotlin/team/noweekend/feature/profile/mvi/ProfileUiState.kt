package team.noweekend.feature.profile.mvi

import androidx.compose.runtime.Stable
import team.noweekend.core.common.android.mvi.UiState
import team.noweekend.core.model.user.User

@Stable
data class ProfileUiState(
    val isLoading: Boolean,
    val user: User,
) : UiState {
    companion object {
        val INITIAL_STATE: ProfileUiState = ProfileUiState(
            isLoading = false,
            user = User(
                userId = "",
                userEmail = "",
                userName = "",
                userGender = "",
                userBirth = "",
                oAuthId = "",
                oAuthType = "",
                revocableToken = "",
                role = "",
                remainingAnnualLeave = 0f,
                latitude = null,
                longitude = null,
                averageTemperature = 0f,
            ),
        )
    }
}
