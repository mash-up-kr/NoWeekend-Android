package team.noweekend.catalog.example.screen

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest
import team.noweekend.catalog.example.mvi.ExampleIntent
import team.noweekend.catalog.example.mvi.ExampleSideEffectHandler
import team.noweekend.catalog.example.mvi.ExampleUiState
import team.noweekend.catalog.example.mvi.ExampleViewModel
import team.noweekend.catalog.example.mvi.rememberExampleSideEffectHandler

@Composable
internal fun ExampleRoute(
    navigateToHistoryBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ExampleViewModel = hiltViewModel(),
) {
    BackHandler {
        viewModel.intent(ExampleIntent.ClickBackButton)
    }

    val uiState: ExampleUiState by viewModel.uiState.collectAsStateWithLifecycle()
    val sideEffectHandler: ExampleSideEffectHandler = rememberExampleSideEffectHandler(
        navigateToHistoryBack = navigateToHistoryBack,
    )

    LaunchedEffect(key1 = Unit) {
        viewModel.sideEffect.collectLatest {
            sideEffectHandler.handleSideEffect(it)
        }
    }

    ExampleScreen(
        onBackClick = { viewModel.intent(ExampleIntent.ClickBackButton) },
        uiState = uiState,
        modifier = modifier,
    )
}
