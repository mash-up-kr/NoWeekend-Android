package team.noweekend.feature.create.vacation.information.component.button.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import team.noweekend.feature.create.vacation.information.model.InformationRadioGroupUiModel

internal class PreviewInformationSelectRadioButtonProvider : PreviewParameterProvider<InformationRadioGroupUiModel> {
    override val values: Sequence<InformationRadioGroupUiModel>
        get() = sequenceOf(
            InformationRadioGroupUiModel(
                text = "계획형",
                isSelected = true,
                tag = "AT_HOME",
            ),
            InformationRadioGroupUiModel(
                text = "즉흥 자유형",
                isSelected = true,
                tag = "AT_HOME",
            ),
            InformationRadioGroupUiModel(
                text = "야외 활동",
                isSelected = false,
                tag = "AT_HOME",
            ),
            InformationRadioGroupUiModel(
                text = "집콕",
                isSelected = false,
                tag = "AT_HOME",
            ),
        )
}

internal class PreviewInformationSelectRadioGroupProvider :
    PreviewParameterProvider<ImmutableList<InformationRadioGroupUiModel>> {
    override val values: Sequence<ImmutableList<InformationRadioGroupUiModel>>
        get() = sequenceOf(
            persistentListOf(
                InformationRadioGroupUiModel(
                    text = "계획형",
                    isSelected = true,
                    tag = "AT_HOME",
                ),
                InformationRadioGroupUiModel(
                    text = "즉흥 자유형",
                    isSelected = false,
                    tag = "AT_HOME",
                ),
            ),
        )
}
