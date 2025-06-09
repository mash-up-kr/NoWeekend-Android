package team.noweekend.catalog.example.mvi

import team.noweekend.catalog.model.Example
import team.noweekend.core.common.android.mvi.UiState

data class ExampleUiState(
    val isLoading: Boolean,
    val example: Example,
) : UiState {
    companion object {
        val INITIAL_STATE: ExampleUiState = ExampleUiState(
            isLoading = true,
            example = Example(
                name = "",
                description = "",
                content = {},
            ),
        )
    }
}
