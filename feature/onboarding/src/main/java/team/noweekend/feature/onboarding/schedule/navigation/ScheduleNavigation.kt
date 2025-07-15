package team.noweekend.feature.onboarding.schedule.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import kotlinx.collections.immutable.ImmutableList
import team.noweekend.core.common.ui.schedule.model.FrequentSchedule
import team.noweekend.core.navigator.model.Onboard
import team.noweekend.feature.onboarding.mvi.OnboardUiState
import team.noweekend.feature.onboarding.schedule.screen.FrequentScheduleRoute

internal fun NavHostController.navigateToSchedule() {
    navigate(Onboard.Schedule)
}

internal fun NavGraphBuilder.scheduleGraph(
    uiState: OnboardUiState,
    onBackClick: () -> Unit,
    onConfirmClick: (ImmutableList<FrequentSchedule>) -> Unit,
    onSelectedChip: (FrequentSchedule) -> Unit,
) {
    composable<Onboard.Schedule> {
        FrequentScheduleRoute(
            uiState = uiState,
            onBackClick = onBackClick,
            onConfirmClick = onConfirmClick,
            onSelectedChip = onSelectedChip,
        )
    }
}
