package team.noweekend.catalog.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import team.noweekend.catalog.home.screen.HomeRoute
import team.noweekend.catalog.model.Component
import team.noweekend.catalog.navigation.CatalogRoute

internal fun NavGraphBuilder.catalogHomeScreen(
    navigateToComponentDetail: (Component) -> Unit,
) {
    composable<CatalogRoute.Home> {
        HomeRoute(
            navigateToComponentDetail = navigateToComponentDetail,
        )
    }
}
