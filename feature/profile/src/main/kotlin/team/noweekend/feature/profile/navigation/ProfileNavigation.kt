package team.noweekend.feature.profile.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import team.noweekend.core.navigator.model.Profile
import team.noweekend.feature.profile.screen.ProfileRoute

fun NavGraphBuilder.profileNavGraph() {
    composable<Profile> {
        ProfileRoute()
    }
}
