package team.noweekend.catalog.home.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest
import team.noweekend.catalog.home.mvi.CatalogHomeSideEffectHandler
import team.noweekend.catalog.home.mvi.HomeIntent
import team.noweekend.catalog.home.mvi.HomeUiState
import team.noweekend.catalog.home.mvi.HomeViewModel
import team.noweekend.catalog.home.mvi.rememberCatalogHomeSideEffectHandler
import team.noweekend.catalog.model.Component

@Composable
internal fun HomeRoute(
    navigateToComponentDetail: (Component) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val uiState: HomeUiState by viewModel.uiState.collectAsStateWithLifecycle()
    val sideEffectHandler: CatalogHomeSideEffectHandler = rememberCatalogHomeSideEffectHandler(
        navigateToComponentDetail = navigateToComponentDetail,
    )

    LaunchedEffect(key1 = Unit) {
        viewModel.sideEffect.collectLatest { sideEffect ->
            sideEffectHandler.handleSideEffect(sideEffect)
        }
    }

    HomeScreen(
        modifier = modifier,
        uiState = uiState,
        onComponentClick = { viewModel.intent(HomeIntent.ClickComponent(it)) },
    )
}
