package team.noweekend.catalog.example.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import team.noweekend.catalog.example.screen.ExampleRoute
import team.noweekend.catalog.navigation.CatalogRoute

internal fun NavHostController.navigateToExampleDetail(componentId: Int, exampleIndex: Int) {
    this.navigate(
        CatalogRoute.Example(
            componentId = componentId,
            exampleIndex = exampleIndex,
        ),
    )
}

internal fun NavGraphBuilder.exampleScreen(
    navigateToHistoryBack: () -> Unit,
) {
    composable<CatalogRoute.Example> {
        ExampleRoute(
            navigateToHistoryBack = navigateToHistoryBack,
        )
    }
}
