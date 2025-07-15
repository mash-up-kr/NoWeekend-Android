package team.noweekend.feature.main.screen

import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import team.noweekend.feature.main.navigation.MainNavigator
import team.noweekend.feature.main.navigation.rememberMainNavigator

@Composable
internal fun MainRoute(
    navigateToCreateVacation: ((Intent.() -> Intent)?, ActivityResultLauncher<Intent>?) -> Unit,
    navigateToDetailDate : ((Intent.() -> Intent)?) -> Unit,
    modifier: Modifier = Modifier,
    navigator: MainNavigator = rememberMainNavigator(),
) {
    MainScreen(
        navigateToCreateVacation = navigateToCreateVacation,
        onTabSelected = { navigator.navigate(it) },
        modifier = modifier,
        navigator = navigator,
        navigateToDetailDate = navigateToDetailDate
    )
}
