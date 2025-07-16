package team.noweekend.feature.detail.date.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import team.noweekend.feature.detail.date.model.DegreeUIModel

internal class PreviewDegreeCardParameterProvider :
    PreviewParameterProvider<DegreeUIModel> {

    override val values = sequenceOf(
        DegreeUIModel(
            degree = 0,
            isAnnualLeave = false,
        ),
        DegreeUIModel(
            degree = 25,
            isAnnualLeave = false,
        ),
        DegreeUIModel(
            degree = 50,
            isAnnualLeave = false,
        ),
        DegreeUIModel(
            degree = 100,
            isAnnualLeave = false,
        ),
        DegreeUIModel(
            degree = 0,
            isAnnualLeave = true,
        ),
        DegreeUIModel(
            degree = 25,
            isAnnualLeave = true,
        ),
        DegreeUIModel(
            degree = 50,
            isAnnualLeave = true,
        ),
        DegreeUIModel(
            degree = 100,
            isAnnualLeave = true,
        ),
    )
}
