package team.noweekend.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import team.noweekend.core.navigator.model.DestinationRoute

@Composable
internal fun rememberNavigationTabNavController(
    navController: NavHostController = rememberNavController(),
): NavigationTabNavController = remember(navController) {
    NavigationTabNavController(navController)
}

internal class NavigationTabNavController(
    private val navController: NavHostController,
) {
    val startDestination: DestinationRoute = NavigationTab.HOME.route

    val currentTab: NavigationTab
        @Composable
        get() = NavigationTab.find { destinationRoute ->
            currentDestination?.hasRoute(destinationRoute::class) == true
        } ?: NavigationTab.HOME

    private val currentDestination: NavDestination?
        @Composable
        get() = navController.currentBackStackEntryAsState().value?.destination

    fun navigate(tab: NavigationTab) {
        val navOptions = navOptions {
            popUpTo(navController.graph.id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

        when (tab) {
            NavigationTab.HOME -> navController.navigate(DestinationRoute.Home, navOptions)
            NavigationTab.CALENDAR -> navController.navigate(DestinationRoute.Calendar, navOptions)
            NavigationTab.PROFILE -> navController.navigate(DestinationRoute.Profile, navOptions)
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
