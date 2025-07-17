package team.noweekend.feature.home.mvi

import team.noweekend.core.common.android.mvi.Intent
import team.noweekend.feature.home.model.HolidayUiModel

sealed interface HomeIntent : Intent {
    data class CreateVacation(
        val a: String,
    ) : HomeIntent

    data object ClickCreateVacation : HomeIntent
    data class ClickHolidayVacationCard(
        val holiday: HolidayUiModel,
    ) : HomeIntent

    data object ClickRecommendationVacationCard : HomeIntent
    data object ClickPopularVacation : HomeIntent
    sealed interface BottomSheet : HomeIntent {
        data class ClickAddTaskButton(
            val title: String,
        ) : BottomSheet

        data object DismissTaskTitleBottomSheet : BottomSheet
    }
}
