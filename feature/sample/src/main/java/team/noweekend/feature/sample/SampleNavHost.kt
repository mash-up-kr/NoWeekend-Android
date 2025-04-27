package team.noweekend.feature.sample

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import team.noweekend.core.navigator.route.Sample
import team.noweekend.feature.sample.navigation.sampleScreen

@Composable
internal fun SampleNavHost(
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Sample.Home,
    ) {
        sampleScreen()
    }
}
