package team.noweekend.feature.sample.home.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest
import team.noweekend.feature.sample.home.mvi.SampleIntent
import team.noweekend.feature.sample.home.mvi.SampleSideEffectHandler
import team.noweekend.feature.sample.home.mvi.SampleViewModel
import team.noweekend.feature.sample.home.mvi.rememberSampleSideEffectHandler

@Composable
internal fun SampleRoute(
    modifier: Modifier = Modifier,
    viewModel: SampleViewModel = hiltViewModel(),
    navigateToMemberDetail: (List<String>) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val sideEffectHandler: SampleSideEffectHandler = rememberSampleSideEffectHandler(
        navigateToMemberDetail = navigateToMemberDetail,
    )

    LaunchedEffect(key1 = Unit) {
        viewModel.sideEffect.collectLatest { sideEffect ->
            sideEffectHandler.handleSideEffect(sideEffect)
        }
    }

    SampleScreen(
        modifier = modifier,
        uiState = uiState,
        onDetailButtonClick = { viewModel.intent(SampleIntent.ClickMemberDetailButton) }
    )
}
