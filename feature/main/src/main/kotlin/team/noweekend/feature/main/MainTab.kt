package team.noweekend.feature.main

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import team.noweekend.core.navigator.model.DestinationRoute
import team.noweekend.core.resource.NWKDrawableResource
import team.noweekend.core.resource.NWKStringResource

internal enum class MainTab(
    @StringRes val labelId: Int,
    @DrawableRes val selectedIconResId: Int,
    @DrawableRes val unselectedIconResId: Int,
    val route: DestinationRoute,
) {
    HOME(
        labelId = NWKStringResource.LabelHome,
        selectedIconResId = NWKDrawableResource.HomeOn,
        unselectedIconResId = NWKDrawableResource.HomeOff,
        route = DestinationRoute.Home,
    ),
    CALENDAR(
        labelId = NWKStringResource.LabelCalendar,
        selectedIconResId = NWKDrawableResource.CalendarOn,
        unselectedIconResId = NWKDrawableResource.CalendarOff,
        route = DestinationRoute.Calendar,
    ),
    PROFILE(
        labelId = NWKStringResource.LabelProfile,
        selectedIconResId = NWKDrawableResource.PersonOn,
        unselectedIconResId = NWKDrawableResource.PersonOff,
        route = DestinationRoute.Profile,
    ),
    ;

    companion object {
        @Composable
        fun find(isRouteMatch: @Composable (DestinationRoute) -> Boolean): MainTab? {
            return entries.find { isRouteMatch(it.route) }
        }

        @Composable
        fun contains(predicate: @Composable (DestinationRoute) -> Boolean): Boolean {
            return entries.map { it.route }.any { predicate(it) }
        }
    }
}
