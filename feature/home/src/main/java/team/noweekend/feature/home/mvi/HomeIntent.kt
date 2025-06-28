package team.noweekend.feature.home.mvi

import team.noweekend.core.common.android.mvi.Intent

sealed interface HomeIntent : Intent {
    data object ClickCreateVacation : HomeIntent
}
