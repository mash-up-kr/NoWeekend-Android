package team.noweekend.feature.detail.date.component.degreeGauge.core

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.noweekend.core.design.system.foundation.theme.NWKTheme

@Composable
internal fun DegreeGaugeBar(
    degree: Int,
    isAnnualLeave: Boolean,
    modifier: Modifier = Modifier,
) {
    val backgroundColor = NWKTheme.color.Neutral.neutralGray100
    val barColor = NWKTheme.color.Toast.toast400
    val dividerColor = if (isAnnualLeave) NWKTheme.color.TaskItem.annualLeave else NWKTheme.color.Toast.toast800
    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(16.dp),
    ) {
        val barWidth = size.width
        val barHeight = size.height
        val onePx = 1.dp.toPx()

        /**
         * 0 ~ 25까지
         * 25 : barWidth / 3 = degree : x
         * x = (degree * barWidth / 3) / 25
         *
         * 25 ~ 50까지
         * start = barWidth / 3
         * degree는 실제 0 ~ 25까지 변화 -> degree - 25
         * 25 ~ 50 칸의 너비 = barWidth / 3
         * 칸의 변화 비율 = degree - 25 / 25
         * start + 25 ~ 50 칸의 너비 * 칸의 변화 비율
         *
         *
         * 50 ~ 100까지
         * start = barWidth * 2 / 3
         * degree는 실제 0 ~ 50까지 변화 -> degree - 50
         * 50 ~ 100 칸의 너비 = barWidth / 3 - 1px
         * 칸의 변화 비율 = degree - 50 / 50
         * start + 50 ~ 100 칸의 너비 * 칸의 변화 비율
         */

        val progressWidth = when {
            degree <= 0 -> 0f

            degree <= 25 -> {
                (barWidth / 3 * degree) / 25f
            }

            degree <= 50 -> {
                val start = barWidth / 3
                start + (barWidth / 3 * ((degree - 25) / 25f))
            }

            else -> {
                val start = barWidth * 2 / 3
                start + (barWidth / 3 - onePx) * ((degree - 50) / 50f)
            }
        }.coerceIn(0f, barWidth - onePx)

        drawRect(
            color = backgroundColor,
            size = Size(barWidth, barHeight),
        )

        if (isAnnualLeave.not()) {
            drawRect(
                color = barColor,
                size = Size(progressWidth, barHeight),
            )
        }

        val dividerOffsetList = listOf(
            0f,
            barWidth / 3,
            barWidth * 2 / 3,
            barWidth - 1.dp.toPx(),
        )

        dividerOffsetList.forEach { offset ->
            drawRect(
                color = dividerColor,
                size = Size(1.dp.toPx(), barHeight),
                topLeft = Offset(offset, 0f),
            )
        }
    }
}

@Preview
@Composable
private fun PreviewDegreeGaugeBar() {
    NWKTheme {
        DegreeGaugeBar(
            modifier = Modifier.fillMaxWidth(),
            degree = 30,
            isAnnualLeave = false,
        )
    }
}
