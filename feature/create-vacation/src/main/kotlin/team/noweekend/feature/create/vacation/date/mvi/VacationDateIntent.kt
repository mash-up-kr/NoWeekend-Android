package team.noweekend.feature.create.vacation.date.mvi

import team.noweekend.core.common.android.mvi.Intent

sealed interface VacationDateIntent : Intent {
    data object ClickBackButton : VacationDateIntent
    data class ClickNextButton(
        val date: String,
    ) : VacationDateIntent
}
