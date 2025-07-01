package team.noweekend.feature.create.vacation.date.mvi

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import team.noweekend.core.common.android.mvi.UiState

@Stable
data class VacationDateUiState(
    val isLoading: Boolean,
    val usageDays: Int,
    val remainedDays: Int?,
) : UiState {

    val isButtonEnabled: Boolean
        @Composable get() = remember {
            derivedStateOf { validateNextButtonEnableStatus(usageDays, remainedDays) }
        }.value

    private fun validateNextButtonEnableStatus(usageDays: Int, remainedDays: Int?): Boolean {
        remainedDays ?: return false
        return when {
            usageDays < 1 -> false
            usageDays > remainedDays -> false
            else -> true
        }
    }

    companion object {
        val INITIAL_STATE: VacationDateUiState = VacationDateUiState(
            isLoading = false,
            usageDays = 0,
            remainedDays = null,
        )

        val DUMMY_STATE: VacationDateUiState = VacationDateUiState(
            isLoading = false,
            usageDays = 0,
            remainedDays = null,
        )
    }
}
