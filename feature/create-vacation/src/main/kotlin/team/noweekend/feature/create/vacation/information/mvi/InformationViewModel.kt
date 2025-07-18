package team.noweekend.feature.create.vacation.information.mvi

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.collections.immutable.toImmutableMap
import team.noweekend.core.common.android.base.MVIViewModel
import team.noweekend.core.navigator.model.CreateVacation
import team.noweekend.feature.create.vacation.information.model.InformationRadioGroupUiModel
import javax.inject.Inject

@HiltViewModel
class InformationViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : MVIViewModel<InformationIntent, InformationSideEffect, InformationUiState>(
    savedStateHandle = savedStateHandle,
) {
    override fun createInitialState(savedStateHandle: SavedStateHandle): InformationUiState {
        val route: CreateVacation.Information = savedStateHandle.toRoute<CreateVacation.Information>()
        val days: Int = route.date
        return InformationUiState.INITIAL_STATE.copy(days = days)
    }

    override fun handleClientException(throwable: Throwable) {}

    override suspend fun handleIntent(intent: InformationIntent) {
        when (intent) {
            is InformationIntent.ClickBackButton -> navigateToHistoryBack()
            is InformationIntent.ClickBbaSsakButton -> {
                val (travelStyle, activityType, restPreference, leisurePreference) = getTags()
                navigateToHome(
                    days = currentState.days,
                    travelStyle = travelStyle,
                    activityType = activityType,
                    restPreference = restPreference,
                    leisurePreference = leisurePreference,
                )
            }

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

    private fun getTags(): List<String> {
        return currentState.informationData
            .values
            .flatten()
            .filter { it.isSelected }
            .map { it.tag }
    }

    private fun navigateToHistoryBack() = execute {
        postSideEffect(InformationSideEffect.NavigateToHistoryBack)
    }

    private fun navigateToHome(
        days: Int,
        travelStyle: String,
        activityType: String,
        restPreference: String,
        leisurePreference: String,
    ) = execute {
        postSideEffect(
            InformationSideEffect.NavigateToHome(
                days = days,
                travelStyle = travelStyle,
                activityType = activityType,
                restPreference = restPreference,
                leisurePreference = leisurePreference,
            ),
        )
    }
}
