package team.noweekend.feature.sample

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import team.noweekend.core.navigator.model.Sample
import team.noweekend.feature.sample.detail.navigation.navigateToSampleDetail
import team.noweekend.feature.sample.detail.navigation.sampleDetailScreen
import team.noweekend.feature.sample.home.navigation.sampleScreen

@Composable
internal fun SampleNavHost(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Sample.Home
    ) {
        sampleScreen(
            navigateToMemberDetail = navController::navigateToSampleDetail,
        )
        sampleDetailScreen(
            navigateToHistoryBack = navController::navigateUp,
        )
    }
}
