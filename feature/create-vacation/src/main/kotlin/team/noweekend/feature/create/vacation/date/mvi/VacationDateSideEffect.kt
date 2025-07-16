package team.noweekend.feature.create.vacation.date.mvi

import team.noweekend.core.common.android.mvi.SideEffect

sealed interface VacationDateSideEffect : SideEffect {
    data object NavigateToHistoryBack : VacationDateSideEffect
    data object NavigateToInformation : VacationDateSideEffect
}
