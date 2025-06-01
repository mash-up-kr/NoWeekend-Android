package team.noweekend.catalog.component.mvi

import team.noweekend.catalog.model.Component
import team.noweekend.core.common.android.mvi.UiState

data class ComponentUiState(
    val isLoading: Boolean,
    val component: Component,
) : UiState {
    companion object {
        val INITIAL_STATE: ComponentUiState = ComponentUiState(
            isLoading = true,
            component = Component(
                id = 0,
                name = "",
                imageUrl = "",
                description = "",
                examples = emptyList(),
            ),
        )
    }
}
