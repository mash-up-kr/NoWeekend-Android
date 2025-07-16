package team.noweekend.feature.detail.date.component.degreeGauge.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class PreviewDegreeGaugeParameterProvider : PreviewParameterProvider<Pair<Int, Boolean>> {
    override val values: Sequence<Pair<Int, Boolean>> = sequenceOf(
        Pair(0, true),
        Pair(0, false),
        Pair(24, false),
        Pair(25, false),
        Pair(26, false),
        Pair(49, false),
        Pair(50, false),
        Pair(51, false),
        Pair(75, false),
        Pair(99, false),
        Pair(100, false),
    )
}
