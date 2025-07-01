package team.noweekend.feature.create.vacation.information.component.button.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import team.noweekend.feature.create.vacation.information.model.SelectedInformationUiModel

internal class PreviewInformationSelectRadioButtonProvider : PreviewParameterProvider<SelectedInformationUiModel> {
    override val values: Sequence<SelectedInformationUiModel>
        get() = sequenceOf(
            SelectedInformationUiModel(
                text = "계획형",
                isSelected = true,
            ),
            SelectedInformationUiModel(
                text = "즉흥 자유형",
                isSelected = true,
            ),
            SelectedInformationUiModel(
                text = "야외 활동",
                isSelected = false,
            ),
            SelectedInformationUiModel(
                text = "집콕",
                isSelected = false,
            ),
        )
}
