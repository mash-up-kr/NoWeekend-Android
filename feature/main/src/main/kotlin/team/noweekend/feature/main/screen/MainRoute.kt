package team.noweekend.feature.main.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import team.noweekend.feature.main.navigation.MainNavigator
import team.noweekend.feature.main.navigation.rememberMainNavigator

@Composable
internal fun MainRoute(
    navigateToCreateVacation: () -> Unit,
    modifier: Modifier = Modifier,
    navigator: MainNavigator = rememberMainNavigator(),
) {
    MainScreen(
        navigateToCreateVacation = navigateToCreateVacation,
        onTabSelected = { navigator.navigate(it) },
        modifier = modifier,
        navigator = navigator,
    )
}
