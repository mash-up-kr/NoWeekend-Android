package team.noweekend.feature.create.vacation.information.mvi

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.collections.immutable.toImmutableMap
import team.noweekend.core.common.android.base.MVIViewModel
import team.noweekend.feature.create.vacation.information.model.InformationRadioGroupUiModel
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
            is InformationIntent.ClickBackButton -> navigateToHistoryBack()
            is InformationIntent.ClickNextButton -> navigateToRecommendResult()
            is InformationIntent.SelectInformation -> updateInformationCheckStatus(intent.row, intent.information)
        }
    }

    private fun updateInformationCheckStatus(
        selectedRow: Int,
        information: InformationRadioGroupUiModel,
    ) {
        val updatedInformation = currentState.informationData
            .map { (row, informationList) ->
                if (row == selectedRow) {
                    row to informationList.map { uiModel ->
                        uiModel.copy(isSelected = uiModel == information)
                    }.toImmutableList()
                } else {
                    row to informationList
                }
            }.toMap().toImmutableMap()

        reduce {
            copy(
                informationData = updatedInformation,
            )
        }
    }

    private fun navigateToHistoryBack() = execute {
        postSideEffect(InformationSideEffect.NavigateToHistoryBack)
    }

    private fun navigateToRecommendResult() = execute {
        postSideEffect(InformationSideEffect.NavigateToVacationRecommendation)
    }
}
