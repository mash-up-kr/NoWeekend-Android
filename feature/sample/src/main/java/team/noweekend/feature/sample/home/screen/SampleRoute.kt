package team.noweekend.feature.sample.home.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest
import team.noweekend.feature.sample.home.mvi.SampleIntent
import team.noweekend.feature.sample.home.mvi.SampleSideEffect
import team.noweekend.feature.sample.home.mvi.SampleViewModel

@Composable
internal fun SampleRoute(
    modifier: Modifier = Modifier,
    viewModel: SampleViewModel = hiltViewModel(),
    navigateToMemberDetail: (List<String>) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = viewModel) {
        viewModel.sideEffect.collectLatest { sideEffect ->
            when (sideEffect) {
                is SampleSideEffect.NavigateToMemberDetail -> {
                    navigateToMemberDetail(sideEffect.members)
                }
            }
        }
    }

    SampleScreen(
        modifier = modifier,
        uiState = uiState,
        onDetailButtonClick = { viewModel.intent(SampleIntent.ClickMemberDetailButton) }
    )
}
