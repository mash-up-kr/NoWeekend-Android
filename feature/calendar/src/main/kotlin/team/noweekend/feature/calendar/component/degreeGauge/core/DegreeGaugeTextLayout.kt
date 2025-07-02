package team.noweekend.feature.calendar.component.degreeGauge.core

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.core.resource.NWKStringResource.HomeAverageTemperatureTextSpan2

@Composable
internal fun DegreeGaugeTextLayout(
    modifier: Modifier = Modifier,
) {
    Layout(
        modifier = modifier,
        content = {
            Text(
                text = stringResource(id = HomeAverageTemperatureTextSpan2, 0),
                style = NWKTheme.typography.body3,
                color = NWKTheme.color.Neutral.neutralGray900,
            )
            Text(
                text = stringResource(id = HomeAverageTemperatureTextSpan2, 25),
                style = NWKTheme.typography.body3,
                color = NWKTheme.color.Neutral.neutralGray900,
            )
            Text(
                text = stringResource(id = HomeAverageTemperatureTextSpan2, 50),
                style = NWKTheme.typography.body3,
                color = NWKTheme.color.Neutral.neutralGray900,
            )
            Text(
                text = stringResource(id = HomeAverageTemperatureTextSpan2, 100),
                style = NWKTheme.typography.body3,
                color = NWKTheme.color.Neutral.neutralGray900,
            )
        },
    ) { measurables: List<Measurable>, constraints: Constraints ->


        /**
         * textConstraints를 사용하지 않고, constraints 람다를 사용하면,
         * 텍스트의 크기가 화면 사이즈가 같아짐.
         */
        val textConstraints = Constraints(
            minWidth = 0,
            maxWidth = Constraints.Infinity,
            minHeight = 0,
            maxHeight = constraints.maxHeight
        )

        val placeables: List<Placeable> =
            measurables.map { measurable: Measurable -> measurable.measure(textConstraints) }


        val zeroText = placeables[0]
        val twentyFiveText = placeables[1]
        val fiftyText = placeables[2]
        val hundredText = placeables[3]

        val layoutWidth = constraints.maxWidth
        val layoutHeight = placeables.maxOf { it.height }


        layout(layoutWidth, layoutHeight) {
            zeroText.placeRelative(x = 0, y = 0)
            twentyFiveText.placeRelative(x = layoutWidth / 3 - twentyFiveText.width / 2, y = 0)
            fiftyText.placeRelative(x = layoutWidth / 3 * 2 - fiftyText.width / 2, y = 0)
            hundredText.placeRelative(x = layoutWidth - hundredText.width, y = 0)
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun PreviewDegreeGauge() {
    NWKTheme {
        DegreeGaugeTextLayout(
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
