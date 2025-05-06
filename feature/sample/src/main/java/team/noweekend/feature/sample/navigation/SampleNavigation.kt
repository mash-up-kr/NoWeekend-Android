package team.noweekend.feature.sample.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import team.noweekend.core.navigator.model.Sample
import team.noweekend.feature.sample.screen.SampleRoute

internal fun NavGraphBuilder.sampleScreen() {
    composable<Sample.Home> {
        SampleRoute()
    }
}