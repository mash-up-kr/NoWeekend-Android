package team.noweekend.feature.onboarding.profile.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import team.noweekend.core.navigator.model.Onboard
import team.noweekend.feature.onboarding.profile.screen.ProfileInputRoute

internal fun NavGraphBuilder.profileGraph() {
    composable<Onboard.Profile> {
        ProfileInputRoute()
    }
}
