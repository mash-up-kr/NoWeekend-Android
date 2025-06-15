package team.noweekend.feature.calendar.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import team.noweekend.core.navigator.model.DestinationRoute
import team.noweekend.feature.calendar.screen.CalendarRoute

fun NavGraphBuilder.calendarScreen() {
    composable<DestinationRoute.Calendar> {
        CalendarRoute()
    }
}
