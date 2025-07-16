package team.noweekend.feature.home.navigation

import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import team.noweekend.core.navigator.model.Home
import team.noweekend.feature.home.screen.HomeRoute

fun NavGraphBuilder.homeNavGraph(
    navigateToCreateVacation: ((Intent.() -> Intent)?, ActivityResultLauncher<Intent>?) -> Unit,
) {
    composable<Home> {
        HomeRoute(
            navigateToCreateVacation = navigateToCreateVacation,
        )
    }
}
