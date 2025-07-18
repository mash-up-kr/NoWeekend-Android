package team.noweekend.feature.create.vacation.information.mvi

import team.noweekend.core.common.android.mvi.SideEffect

sealed interface InformationSideEffect : SideEffect {
    data object NavigateToHistoryBack : InformationSideEffect
    data class NavigateToHome(
        val days: Int,
        val travelStyle: String,
        val activityType: String,
        val restPreference: String,
        val leisurePreference: String,
    ) : InformationSideEffect
}
