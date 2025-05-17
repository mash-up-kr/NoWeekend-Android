package team.noweekend.feature.sample.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import team.noweekend.core.navigator.model.Sample
import team.noweekend.feature.sample.home.screen.SampleRoute

internal fun NavGraphBuilder.sampleScreen(
    navigateToMemberDetail: (List<String>) -> Unit,
) {
    composable<Sample.Home> {
        SampleRoute(
            navigateToMemberDetail = navigateToMemberDetail,
        )
    }
}
