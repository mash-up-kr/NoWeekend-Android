package team.noweekend.feature.create.vacation.date.mvi

import team.noweekend.core.common.android.mvi.UiState

data class VacationDateUiState(
    val isLoading: Boolean,
    val remainedDays: Int?,
) : UiState {
    companion object {
        val INITIAL_STATE: VacationDateUiState = VacationDateUiState(
            isLoading = false,
            remainedDays = null
        )

        val DUMMY_STATE: VacationDateUiState = VacationDateUiState(
            isLoading = false,
            remainedDays = null
        )
    }
}
