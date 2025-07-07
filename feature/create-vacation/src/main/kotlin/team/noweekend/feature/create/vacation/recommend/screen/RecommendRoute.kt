package team.noweekend.feature.create.vacation.recommend.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest
import team.noweekend.feature.create.vacation.recommend.mvi.RecommendSideEffectHandler
import team.noweekend.feature.create.vacation.recommend.mvi.RecommendUiState
import team.noweekend.feature.create.vacation.recommend.mvi.RecommendViewModel
import team.noweekend.feature.create.vacation.recommend.mvi.rememberRecommendSideEffectHandler

@Composable
internal fun RecommendRoute(
    modifier: Modifier = Modifier,
    viewModel: RecommendViewModel = hiltViewModel(),
) {
    val uiState: State<RecommendUiState> = viewModel.uiState.collectAsStateWithLifecycle()
    val sideEffectHandler: RecommendSideEffectHandler = rememberRecommendSideEffectHandler(
        navigateToHistoryBack = {},
    )

    LaunchedEffect(key1 = Unit) {
        viewModel.sideEffect.collectLatest { sideEffect ->
            sideEffectHandler.handleSideEffect(sideEffect)
        }
    }

    RecommendScreen(
        uiState = uiState,
        modifier = modifier,
        onBackClick = {},
    )
}
