package team.noweekend.catalog.example.mvi

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import team.noweekend.catalog.model.Example
import team.noweekend.catalog.model.NDSComponents
import team.noweekend.catalog.navigation.CatalogRoute
import team.noweekend.core.common.android.base.MVIViewModel
import javax.inject.Inject

@HiltViewModel
class ExampleViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : MVIViewModel<ExampleIntent, ExampleSideEffect, ExampleUiState>(
    savedStateHandle = savedStateHandle,
) {
    override fun createInitialState(savedStateHandle: SavedStateHandle): ExampleUiState {
        val route: CatalogRoute.Example = savedStateHandle.toRoute<CatalogRoute.Example>()
        val example: Example = NDSComponents
            .getOrNull(route.componentId)?.examples
            ?.getOrNull(route.exampleIndex)
            ?: throw IllegalArgumentException()

        return ExampleUiState.INITIAL_STATE.copy(
            example = example,
        )
    }

    override fun handleClientException(throwable: Throwable) {}

    override suspend fun handleIntent(intent: ExampleIntent) {
        when (intent) {
            ExampleIntent.ClickBackButton -> postSideEffect(ExampleSideEffect.NavigateToHistoryBack)
        }
    }
}
