package team.noweekend.feature.create.vacation.information.mvi

import team.noweekend.core.common.android.mvi.Intent

sealed interface InformationIntent : Intent {
    data object ClickBackButton : InformationIntent
    data object ClickNextButton : InformationIntent
}
