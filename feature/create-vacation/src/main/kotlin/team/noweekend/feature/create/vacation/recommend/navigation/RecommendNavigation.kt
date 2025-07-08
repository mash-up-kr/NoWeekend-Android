package team.noweekend.feature.create.vacation.recommend.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import team.noweekend.core.navigator.model.CreateVacation
import team.noweekend.feature.create.vacation.recommend.screen.RecommendRoute

internal fun NavController.navigateToVacationRecommendation() {
    navigate(CreateVacation.Recommend)
}

internal fun NavGraphBuilder.recommendGraph(
    navigateToHistoryBack: () -> Unit,
) {
    composable<CreateVacation.Recommend> {
        RecommendRoute(
            navigateToHistoryBack = navigateToHistoryBack,
        )
    }
}
