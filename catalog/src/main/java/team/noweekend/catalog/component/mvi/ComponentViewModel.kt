package team.noweekend.catalog.component.mvi

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import team.noweekend.catalog.model.Component
import team.noweekend.catalog.model.NDSComponents
import team.noweekend.catalog.navigation.CatalogNavTypeMap
import team.noweekend.catalog.navigation.CatalogRoute
import team.noweekend.core.common.android.base.MVIViewModel
import javax.inject.Inject

@HiltViewModel
class ComponentViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : MVIViewModel<ComponentIntent, ComponentSideEffect, ComponentUiState>(
    savedStateHandle = savedStateHandle,
) {
    override fun createInitialState(savedStateHandle: SavedStateHandle): ComponentUiState {
        val route: CatalogRoute.Component = savedStateHandle.toRoute<CatalogRoute.Component>(
            typeMap = CatalogNavTypeMap.ComponentNavTypeMap,
        )
        val component: Component = NDSComponents.getOrNull(route.componentId) ?: throw IllegalArgumentException()

        return ComponentUiState.INITIAL_STATE.copy(
            component = component,
        )
    }

    override fun handleClientException(throwable: Throwable) {}

    override suspend fun handleIntent(intent: ComponentIntent) {
        when (intent) {
            is ComponentIntent.ClickBackButton -> {
                postSideEffect(ComponentSideEffect.NavigateToHistoryBack)
            }
        }
    }
}
