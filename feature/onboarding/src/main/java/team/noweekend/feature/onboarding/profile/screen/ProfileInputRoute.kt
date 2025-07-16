package team.noweekend.feature.onboarding.profile.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import team.noweekend.feature.onboarding.mvi.OnboardUiState

@Composable
internal fun ProfileInputRoute(
    uiState: OnboardUiState,
    onBackClick: () -> Unit,
    onConfirmClick: (String, String) -> Unit,
    modifier: Modifier = Modifier,
) {
    ProfileInputScreen(
        onBackClick = onBackClick,
        onConfirmClick = onConfirmClick,
        uiState = uiState,
        modifier = modifier.fillMaxSize(),
    )
}
