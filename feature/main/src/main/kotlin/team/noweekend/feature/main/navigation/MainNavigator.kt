package team.noweekend.feature.main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import team.noweekend.core.navigator.model.DestinationRoute
import team.noweekend.feature.main.MainTab

@Composable
internal fun rememberMainNavigator(
    navController: NavHostController = rememberNavController(),
): MainNavigator = remember(navController) {
    MainNavigator(navController)
}

internal class MainNavigator(
    val navController: NavHostController,
) {
    val startDestination: DestinationRoute = MainTab.HOME.route
    val topLevelDestinations: ImmutableList<MainTab> = MainTab.entries.toImmutableList()

    val currentTab: MainTab?
        @Composable
        get() = MainTab.find { destinationRoute ->
            currentDestination?.hasRoute(destinationRoute::class) == true
        }

    private val currentDestination: NavDestination?
        @Composable
        get() = navController.currentBackStackEntryAsState().value?.destination

    fun navigate(tab: MainTab) {
        val navOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

        when (tab) {
            MainTab.HOME -> navController.navigate(DestinationRoute.Home, navOptions)
            MainTab.CALENDAR -> navController.navigate(DestinationRoute.Calendar, navOptions)
            MainTab.PROFILE -> navController.navigate(DestinationRoute.Profile, navOptions)
        }
    }

    private fun popBackStack() {
        navController.popBackStack()
    }

    fun popBackStack(action: () -> Unit) {
        if (isCurrentDestinationSame<DestinationRoute.Home>().not()) {
            popBackStack()
        } else {
            action()
        }
    }

    private inline fun <reified T : DestinationRoute> isCurrentDestinationSame(): Boolean {
        return navController.currentDestination?.hasRoute<T>() == true
    }
}
