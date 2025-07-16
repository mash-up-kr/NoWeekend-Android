package team.noweekend.feature.onboarding.profile.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import team.noweekend.core.navigator.model.Onboard
import team.noweekend.feature.onboarding.mvi.OnboardUiState
import team.noweekend.feature.onboarding.profile.screen.ProfileInputRoute

internal fun NavGraphBuilder.profileGraph(
    uiState: OnboardUiState,
    onBackClick: () -> Unit,
    onConfirmClick: (String, String) -> Unit,
) {
    composable<Onboard.Profile> {
        ProfileInputRoute(
            uiState = uiState,
            onBackClick = onBackClick,
            onConfirmClick = onConfirmClick,
        )
    }
}
