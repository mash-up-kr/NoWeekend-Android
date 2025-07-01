package team.noweekend.feature.create.vacation.information.mvi

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import team.noweekend.core.common.android.base.MVIViewModel
import javax.inject.Inject

@HiltViewModel
class InformationViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : MVIViewModel<InformationIntent, InformationSideEffect, InformationUiState>(
    savedStateHandle = savedStateHandle,
) {
    override fun createInitialState(savedStateHandle: SavedStateHandle): InformationUiState {
        return InformationUiState.DUMMY_STATE
    }

    override fun handleClientException(throwable: Throwable) {}

    override suspend fun handleIntent(intent: InformationIntent) {
        when (intent) {
            InformationIntent.ClickBackButton -> navigateToHistoryBack()
            InformationIntent.ClickNextButton -> navigateToRecommendResult()
        }
    }

    private fun navigateToHistoryBack() = execute {
        postSideEffect(InformationSideEffect.NavigateToHistoryBack)
    }

    private fun navigateToRecommendResult() = execute {
        postSideEffect(InformationSideEffect.NavigateToVacationRecommendation)
    }
}
