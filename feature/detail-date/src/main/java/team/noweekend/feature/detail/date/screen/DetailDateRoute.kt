package team.noweekend.feature.detail.date.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import team.noweekend.feature.detail.date.mvi.DetailDateIntent
import team.noweekend.feature.detail.date.mvi.DetailDateViewModel
import team.noweekend.feature.detail.date.mvi.builder.rememberIntentBuilder

@Composable
internal fun DetailDateRoute(
    onClickBackButton: () -> Unit,
    modifier: Modifier = Modifier,
    detailDateViewModel: DetailDateViewModel = hiltViewModel(),
) {
    val state = detailDateViewModel.uiState.collectAsStateWithLifecycle()

    val intentBuilder = rememberIntentBuilder(
        send = detailDateViewModel::intent,
    )

    LaunchedEffect(Unit) {
        detailDateViewModel.intent(DetailDateIntent.InitState)
    }

    DetailDateScreen(
        modifier = modifier,
        detailDateUiState = state.value,
        onClickCheckBox = intentBuilder::changeCompleteSchedule,
        onClickOptionButton = {},
        onClickBackButton = onClickBackButton,
    )
}
