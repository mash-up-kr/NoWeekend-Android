package team.noweekend.feature.sample.detail.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import team.noweekend.core.navigator.model.Sample
import team.noweekend.feature.sample.detail.screen.SampleDetailRoute

internal fun NavHostController.navigateToSampleDetail(members: List<String>) {
    navigate(Sample.Detail(members))
}

internal fun NavGraphBuilder.sampleDetailScreen() {
    composable<Sample.Detail> {
        SampleDetailRoute()
    }
}
