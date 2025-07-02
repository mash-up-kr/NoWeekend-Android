package team.noweekend.feature.calendar.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import team.noweekend.core.design.system.core.component.image.NWKImage
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.feature.calendar.model.DegreeUIModel
import team.noweekend.feature.calendar.model.DegreeUIModel.Companion.getColor
import team.noweekend.feature.calendar.model.DegreeUIModel.Companion.getResourceImage
import team.noweekend.feature.calendar.model.DegreeUIModel.Companion.getTitle
import team.noweekend.feature.calendar.preview.PreviewDegreeCardParameterProvider

@Composable
fun DegreeCard(
    degreeUIModel: DegreeUIModel,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .width(335.dp)
            .height(200.dp)
            .clip(
                shape = NWKTheme.radius.borderRadius50,
            ),
    ) {
        NWKImage(
            modifier = Modifier.fillMaxSize(),
            drawableResId = degreeUIModel.getResourceImage(),
        )

        Column(
            modifier = Modifier.padding(top = 24.dp, start = 24.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                text = stringResource(id = degreeUIModel.getTitle()),
                style = NWKTheme.typography.heading6,
                color = NWKTheme.color.Neutral.neutralGray900,
            )
            DegreeTextComponent(
                degreeUIModel = degreeUIModel,
            )
        }
    }
}


@Composable
private fun DegreeTextComponent(
    degreeUIModel: DegreeUIModel,
    modifier: Modifier = Modifier,
) {
    Layout(
        modifier = modifier,
        content = {
            Text(
                text = degreeUIModel.degree.toString(),
                style = NWKTheme.typography.heading2,
                color = degreeUIModel.getColor(),
            )
            Text(
                text = "°C",
                style = NWKTheme.typography.heading6,
                color = degreeUIModel.getColor(),
            )
        },
    ) { measurables: List<Measurable>, constraints: Constraints ->
        val placeables =
            measurables.map { measurable: Measurable -> measurable.measure(constraints) }
        val largeText = placeables[0]
        val smallText = placeables[1] // "°C"

        layout(
            width = largeText.width + smallText.width,
            height = largeText.height,
        ) {
            largeText.placeRelative(0, 0)

            /**
             * 온도 텍스트의 하단 Y Offset
             */
            val yOffset: Int = largeText[FirstBaseline]

            /**
             * "°C"의 Text의 여백
             */
            val ySpace: Int = smallText.height - smallText[FirstBaseline]

            /**
             * yOffset만 있으면 큰 텍스트 밑에 부터 배치가 되어서
             * 역으로 smallText의 높이만큼 빼주고, 하단 여백만큼 더해준곳에 배치
             */
            smallText.placeRelative(largeText.width, yOffset - smallText.height + ySpace)
        }
    }
}


@Preview
@Composable
private fun PreviewDegreeCard(
    @PreviewParameter(PreviewDegreeCardParameterProvider::class) model: DegreeUIModel,
) {
    NWKTheme {
        DegreeCard(
            degreeUIModel = model,
        )
    }
}
