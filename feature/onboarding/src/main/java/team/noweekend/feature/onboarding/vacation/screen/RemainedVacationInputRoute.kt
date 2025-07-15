package team.noweekend.feature.onboarding.vacation.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import team.noweekend.feature.onboarding.mvi.OnboardUiState

@Composable
internal fun RemainedVacationInputRoute(
    uiState: OnboardUiState,
    onBackClick: () -> Unit,
    onConfirmClick: (String) -> Unit,
    onHalfVacationClick: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    RemainedVacationInputScreen(
        onBackClick = onBackClick,
        onConfirmClick = onConfirmClick,
        modifier = modifier,
        uiState = uiState,
        onHalfVacationClick = onHalfVacationClick,
    )
}
