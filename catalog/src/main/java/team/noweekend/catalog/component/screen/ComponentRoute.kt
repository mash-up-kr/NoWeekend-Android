package team.noweekend.catalog.component.screen

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest
import team.noweekend.catalog.component.mvi.ComponentIntent
import team.noweekend.catalog.component.mvi.ComponentSideEffectHandler
import team.noweekend.catalog.component.mvi.ComponentUiState
import team.noweekend.catalog.component.mvi.ComponentViewModel
import team.noweekend.catalog.component.mvi.rememberComponentSideEffectHandler

@Composable
internal fun ComponentRoute(
    navigateToHistoryBack: () -> Unit,
    navigateToExample: (Int, Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ComponentViewModel = hiltViewModel(),
) {
    BackHandler {
        viewModel.intent(ComponentIntent.ClickBackButton)
    }

    val uiState: ComponentUiState by viewModel.uiState.collectAsStateWithLifecycle()
    val sideEffectHandler: ComponentSideEffectHandler = rememberComponentSideEffectHandler(
        navigateToHistoryBack = navigateToHistoryBack,
        navigateToExample = navigateToExample,
    )

    LaunchedEffect(key1 = Unit) {
        viewModel.sideEffect.collectLatest {
            sideEffectHandler.handleSideEffect(it)
        }
    }

    ComponentScreen(
        onBackClick = { viewModel.intent(ComponentIntent.ClickBackButton) },
        onExampleClick = { componentId, exampleIndex ->
            viewModel.intent(
                ComponentIntent.ClickExample(
                    componentId = componentId,
                    exampleIndex = exampleIndex,
                ),
            )
        },
        uiState = uiState,
        modifier = modifier,
    )
}
