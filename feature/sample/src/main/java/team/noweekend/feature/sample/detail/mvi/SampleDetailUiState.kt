package team.noweekend.feature.sample.detail.mvi

import androidx.compose.runtime.Stable
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import team.noweekend.core.common.android.mvi.UiState

@Stable
data class SampleDetailUiState(
    val isLoading: Boolean,
    val members: ImmutableList<String>,
) : UiState {
    companion object {
        val INITIAL_STATE: SampleDetailUiState = SampleDetailUiState(
            isLoading = true,
            members = persistentListOf()
        )
    }
}
