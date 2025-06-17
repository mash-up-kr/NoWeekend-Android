package team.noweekend.feature.main.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import team.noweekend.core.design.system.core.component.scaffold.NWKScaffold
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.feature.main.component.NWKNavigationBar
import team.noweekend.feature.main.MainNavHost
import team.noweekend.feature.main.navigation.MainNavigator
import team.noweekend.feature.main.navigation.rememberMainNavigator

@Composable
internal fun MainScreen(
    modifier: Modifier = Modifier,
    navigator: MainNavigator = rememberMainNavigator(),
) {
    NWKScaffold(
        modifier = modifier.fillMaxSize(),
        content = {
            MainNavHost(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                navigator = navigator,
            )
        },
        bottomBar = {
            NWKNavigationBar(
                navigateToTargetTab = { navigator.navigate(it) },
                currentTab = navigator.currentTab,
                topLevelDestinations = navigator.topLevelDestinations,
            )
        },
    )
}

@Preview
@Composable
private fun MainScreenPreview() {
    NWKTheme {
        MainScreen()
    }
}
