package team.noweekend.feature.profile.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import team.noweekend.core.navigator.model.DestinationRoute
import team.noweekend.feature.profile.screen.ProfileRoute

fun NavGraphBuilder.profileScreen() {
    composable<DestinationRoute.Profile> {
        ProfileRoute()
    }
}
