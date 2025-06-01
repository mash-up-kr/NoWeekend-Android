package team.noweekend.catalog

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import team.noweekend.catalog.component.navigation.componentScreen
import team.noweekend.catalog.component.navigation.navigateToComponentDetail
import team.noweekend.catalog.home.navigation.catalogHomeScreen
import team.noweekend.catalog.navigation.CatalogRoute

@Composable
internal fun CatalogNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = CatalogRoute.Home,
    ) {
        catalogHomeScreen(
            navigateToComponentDetail = navController::navigateToComponentDetail,
        )
        componentScreen(
            navigateToHistoryBack = navController::navigateUp,
            navigateToExample = {},
        )
    }
}
