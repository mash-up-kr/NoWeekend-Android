package team.noweekend.catalog.home.mvi

import androidx.compose.runtime.Stable
import kotlinx.collections.immutable.ImmutableList
import team.noweekend.catalog.model.Component
import team.noweekend.catalog.model.NDSComponents
import team.noweekend.core.common.android.mvi.UiState

@Stable
data class HomeUiState(
    val isLoading: Boolean,
    val components: ImmutableList<Component>,
) : UiState {
    companion object {
        val INITIAL_STATE: HomeUiState = HomeUiState(
            isLoading = true,
            components = NDSComponents,
        )
    }
}
