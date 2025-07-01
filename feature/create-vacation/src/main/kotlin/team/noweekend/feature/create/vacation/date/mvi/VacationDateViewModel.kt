package team.noweekend.feature.create.vacation.date.mvi

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import team.noweekend.core.common.android.base.MVIViewModel
import javax.inject.Inject

@HiltViewModel
class VacationDateViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : MVIViewModel<VacationDateIntent, VacationDateSideEffect, VacationDateUiState>(
    savedStateHandle = savedStateHandle,
) {
    override fun createInitialState(savedStateHandle: SavedStateHandle): VacationDateUiState {
        return VacationDateUiState.INITIAL_STATE
    }

    override fun handleClientException(throwable: Throwable) {}

    override suspend fun handleIntent(intent: VacationDateIntent) {
        when (intent) {
            else -> {}
        }
    }
}
