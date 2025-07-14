package team.noweekend.feature.create.vacation.information.mvi

import team.noweekend.core.common.android.mvi.Intent
import team.noweekend.feature.create.vacation.information.model.InformationRadioGroupUiModel

sealed interface InformationIntent : Intent {
    data object ClickBackButton : InformationIntent
    data object ClickBbaSsakButton : InformationIntent
    data class SelectInformation(
        val row: Int,
        val information: InformationRadioGroupUiModel,
    ) : InformationIntent
}
