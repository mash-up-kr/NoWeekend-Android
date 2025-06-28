package team.noweekend.feature.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import team.noweekend.feature.calendar.navigation.calendarNavGraph
import team.noweekend.feature.home.navigation.homeNavGraph
import team.noweekend.feature.main.navigation.MainNavigator
import team.noweekend.feature.profile.navigation.profileNavGraph

@Composable
internal fun MainNavHost(
    navigateToCreateVacation: () -> Unit,
    navigator: MainNavigator,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navigator.navController,
        startDestination = navigator.startDestination,
        modifier = modifier,
    ) {
        homeNavGraph(
            navigateToCreateVacation = navigateToCreateVacation,
        )
        calendarNavGraph()
        profileNavGraph()
    }
}
