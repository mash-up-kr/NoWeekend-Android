package team.noweekend.feature.onboarding.mvi

import androidx.compose.runtime.Stable
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import team.noweekend.core.common.android.mvi.UiState
import team.noweekend.core.common.ui.schedule.model.FrequentSchedule

@Stable
data class OnboardUiState(
    val isLoading: Boolean,
    val nickname: String,
    val birth: String,
    val vacationDay: Int,
    val vacationHour: Int,
    val scheduleTags: ImmutableList<FrequentSchedule>,
) : UiState {
    companion object {
        val INITIAL_STATE = OnboardUiState(
            isLoading = true,
            nickname = "",
            birth = "",
            scheduleTags = emptyList<FrequentSchedule>().toImmutableList(),
            vacationDay = 0,
            vacationHour = 0,
        )
    }
}
