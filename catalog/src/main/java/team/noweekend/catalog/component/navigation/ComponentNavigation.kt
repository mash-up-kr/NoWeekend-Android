package team.noweekend.catalog.component.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import team.noweekend.catalog.component.screen.ComponentRoute
import team.noweekend.catalog.model.Component
import team.noweekend.catalog.model.Example
import team.noweekend.catalog.navigation.CatalogNavTypeMap
import team.noweekend.catalog.navigation.CatalogRoute

internal fun NavHostController.navigateToComponentDetail(component: Component) {
    this.navigate(CatalogRoute.Component(component = component))
}

internal fun NavGraphBuilder.componentScreen(
    navigateToHistoryBack: () -> Unit,
    navigateToExample: (Example) -> Unit,
) {
    composable<CatalogRoute.Component>(
        typeMap = CatalogNavTypeMap.ComponentNavTypeMap,
    ) {
        ComponentRoute(
            navigateToHistoryBack = navigateToHistoryBack,
            navigateToExample = navigateToExample,
        )
    }
}
