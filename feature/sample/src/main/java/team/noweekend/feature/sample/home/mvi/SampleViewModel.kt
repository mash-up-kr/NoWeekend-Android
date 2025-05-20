package team.noweekend.feature.sample.home.mvi

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import team.noweekend.core.common.android.base.MVIViewModel
import javax.inject.Inject

@HiltViewModel
class SampleViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : MVIViewModel<SampleIntent, SampleSideEffect, SampleUiState>(
    savedStateHandle = savedStateHandle,
) {

    override fun createInitialState(savedStateHandle: SavedStateHandle): SampleUiState {
        return SampleUiState.INITIAL_STATE
    }

    override fun handleClientException(throwable: Throwable) {}

    override suspend fun handleIntent(intent: SampleIntent) {
        when (intent) {
            is SampleIntent.ClickBackButton -> navigateBack()
            is SampleIntent.ClickMemberDetailButton -> navigateToMemberDetail()
        }
    }

    private fun navigateBack(): Job = execute {
        postSideEffect(SampleSideEffect.NavigateToHistoryBack)
    }

    private fun navigateToMemberDetail(): Job = execute {
        postSideEffect(
            SampleSideEffect.NavigateToMemberDetail(
                members = listOf("재성", "현국", "정우")
            )
        )
    }
}
