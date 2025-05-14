package team.noweekend.feature.sample.detail.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest
import team.noweekend.feature.sample.detail.mvi.SampleDetailViewModel

@Composable
internal fun SampleDetailRoute(
    modifier: Modifier = Modifier,
    viewModel: SampleDetailViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = viewModel) {
        viewModel.sideEffect.collectLatest { sideEffect ->
            when (sideEffect) {
                else -> {}
            }
        }
    }

    SampleDetailScreen(
        modifier = modifier,
        uiState = uiState,
    )
}
