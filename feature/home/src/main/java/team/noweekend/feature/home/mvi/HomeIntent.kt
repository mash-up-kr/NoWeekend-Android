package team.noweekend.feature.home.mvi

import team.noweekend.core.common.android.mvi.Intent
import team.noweekend.feature.home.model.HolidayUiModel
import team.noweekend.feature.home.model.MonthlyVacationRecommendUiModel
import team.noweekend.feature.home.model.PopularVacationUiModel

sealed interface HomeIntent : Intent {
    data class CreateVacation(
        val days: Int,
        val travelStyle: String,
        val activityType: String,
        val restPreference: String,
        val leisurePreference: String,
    ) : HomeIntent

    data object ClickCreateVacation : HomeIntent
    data class ClickHolidayVacationCard(
        val holiday: HolidayUiModel,
    ) : HomeIntent

    data class ClickRecommendationVacationCard(
        val recommendationVacation: MonthlyVacationRecommendUiModel,
    ) : HomeIntent

    data class ClickPopularVacation(
        val popularVacation: PopularVacationUiModel,
    ) : HomeIntent
    sealed interface BottomSheet : HomeIntent {
        data class ClickAddTaskButton(
            val title: String,
        ) : BottomSheet

        data object DismissTaskTitleBottomSheet : BottomSheet
    }
}
