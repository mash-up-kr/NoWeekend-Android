package team.noweekend.feature.main.screen

import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import team.noweekend.core.design.system.core.component.scaffold.NWKScaffold
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.feature.main.MainNavHost
import team.noweekend.feature.main.MainTab
import team.noweekend.feature.main.component.NWKNavigationBar
import team.noweekend.feature.main.navigation.MainNavigator
import team.noweekend.feature.main.navigation.rememberMainNavigator

@Composable
internal fun MainScreen(
    navigateToCreateVacation: (ActivityResultLauncher<Intent>) -> Unit,
    onTabSelected: (MainTab) -> Unit,
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
                navigateToCreateVacation = navigateToCreateVacation,
            )
        },
        bottomBar = {
            NWKNavigationBar(
                currentTab = navigator.currentTab,
                tabs = navigator.topLevelDestinations,
                onTabSelected = onTabSelected,
            )
        },
    )
}

@Preview
@Composable
private fun MainScreenPreview() {
    NWKTheme {
        MainScreen(
            navigateToCreateVacation = {},
            onTabSelected = {},
        )
    }
}
