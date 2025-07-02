package team.noweekend.feature.calendar.component.degreeGauge

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.feature.calendar.component.degreeGauge.core.DegreeGaugeBar
import team.noweekend.feature.calendar.component.degreeGauge.core.DegreeGaugeTextLayout
import team.noweekend.feature.calendar.component.degreeGauge.preview.PreviewDegreeGaugeParameterProvider

@Composable
internal fun DegreeGauge(
    degree: Int,
    isAnnualLeave: Boolean,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        DegreeGaugeBar(
            modifier = Modifier.fillMaxWidth(),
            degree = degree,
            isAnnualLeave = isAnnualLeave,
        )
        DegreeGaugeTextLayout(
            modifier = Modifier.fillMaxWidth(),
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun PreviewDegreeGauge(
    @PreviewParameter(PreviewDegreeGaugeParameterProvider::class) param: Pair<Int, Boolean>,
) {
    NWKTheme {
        DegreeGauge(
            modifier= Modifier.fillMaxWidth(),
            degree = param.first,
            isAnnualLeave = param.second,
        )
    }
}
