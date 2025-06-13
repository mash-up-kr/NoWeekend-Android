package team.noweekend.navigation

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import team.noweekend.core.navigator.model.DestinationRoute
import team.noweekend.core.resource.R as RR

internal enum class NavigationTab(
    @StringRes val labelId: Int,
    val route: DestinationRoute,
) {
    HOME(
        labelId = RR.string.label_home,
        route = DestinationRoute.Home,
    ),
    CALENDAR(
        labelId = RR.string.label_calendar,
        route = DestinationRoute.Calendar,
    ),
    PROFILE(
        labelId = RR.string.label_profile,
        route = DestinationRoute.Profile,
    ),
    ;

    companion object {
        @Composable
        fun find(isRouteMatch: @Composable (DestinationRoute) -> Boolean): NavigationTab? {
            return entries.find { isRouteMatch(it.route) }
        }

        @Composable
        fun contains(predicate: @Composable (DestinationRoute) -> Boolean): Boolean {
            return entries.map { it.route }.any { predicate(it) }
        }
    }
}
