package team.noweekend.feature.sample.detail.mvi

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Job
import team.noweekend.core.common.android.base.MVIViewModel
import team.noweekend.core.navigator.model.Sample
import javax.inject.Inject

@HiltViewModel
class SampleDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : MVIViewModel<SampleDetailIntent, SampleDetailSideEffect, SampleDetailUiState>(
    savedStateHandle = savedStateHandle,
) {

    override fun createInitialState(savedStateHandle: SavedStateHandle): SampleDetailUiState {
        val route = savedStateHandle.toRoute<Sample.Detail>()
        return SampleDetailUiState.INITIAL_STATE.copy(
            members = route.members.toImmutableList()
        )
    }

    override fun handleClientException(throwable: Throwable) {}

    override suspend fun handleIntent(intent: SampleDetailIntent) {
        when (intent) {
            is SampleDetailIntent.ClickBackButton -> navigateBack()
        }
    }

    private fun navigateBack(): Job = execute {
        postSideEffect(SampleDetailSideEffect.NavigateToHistoryBack)
    }
}
