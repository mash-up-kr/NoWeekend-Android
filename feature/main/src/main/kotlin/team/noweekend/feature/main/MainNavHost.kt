package team.noweekend.feature.main

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import team.noweekend.core.navigator.model.DestinationRoute
import team.noweekend.core.navigator.model.Home
import team.noweekend.feature.calendar.navigation.calendarNavGraph
import team.noweekend.feature.main.navigation.MainNavigator
import team.noweekend.feature.profile.navigation.profileNavGraph

@Composable
internal fun MainNavHost(
    navigator: MainNavigator,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navigator.navController,
        startDestination = navigator.startDestination,
        modifier = modifier,
    ) {
        homeNavGraph()
        calendarNavGraph()
        profileNavGraph()
    }
}

internal fun NavGraphBuilder.homeNavGraph() {
    composable<Home> {
        Text("AFAF")
    }
}
