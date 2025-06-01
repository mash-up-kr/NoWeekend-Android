package team.noweekend.catalog

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import team.noweekend.catalog.model.Components
import team.noweekend.catalog.ui.home.HomeScreen

@Composable
internal fun CatalogNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = CatalogRoute.Home,
    ) {
        composable<CatalogRoute.Home> {
            HomeScreen(
                components = Components,
                onComponentClick = {},
            )
        }
    }
}
