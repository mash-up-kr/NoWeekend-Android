package team.noweekend.feature.onboarding.schedule.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import team.noweekend.core.navigator.model.Onboard
import team.noweekend.feature.onboarding.schedule.screen.FrequentScheduleRoute

internal fun NavHostController.navigateToSchedule() {
    navigate(Onboard.Schedule)
}

internal fun NavGraphBuilder.scheduleGraph() {
    composable<Onboard.Schedule> {
        FrequentScheduleRoute()
    }
}
