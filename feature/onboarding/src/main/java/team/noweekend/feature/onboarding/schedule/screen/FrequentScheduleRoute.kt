package team.noweekend.feature.onboarding.schedule.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.collections.immutable.ImmutableList
import team.noweekend.core.common.ui.schedule.model.FrequentSchedule
import team.noweekend.feature.onboarding.mvi.OnboardUiState

@Composable
internal fun FrequentScheduleRoute(
    uiState: OnboardUiState,
    onBackClick: () -> Unit,
    onConfirmClick: (ImmutableList<FrequentSchedule>) -> Unit,
    onSelectedChip: (FrequentSchedule) -> Unit,
    modifier: Modifier = Modifier,
) {
    FrequentScheduleScreen(
        modifier = modifier.fillMaxSize(),
        onBackClick = onBackClick,
        uiState = uiState,
        onConfirmClick = onConfirmClick,
        onSelectedChip = onSelectedChip,
    )
}
