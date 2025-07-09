package team.noweekend.feature.onboarding.vacation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import team.noweekend.core.navigator.model.Onboard
import team.noweekend.feature.onboarding.vacation.screen.RemainedVacationInputRoute

internal fun NavHostController.navigateToRemainedVacation() {
    navigate(Onboard.RemainedVacation)
}

internal fun NavGraphBuilder.remainedVacationGraph() {
    composable<Onboard.RemainedVacation> {
        RemainedVacationInputRoute()
    }
}
