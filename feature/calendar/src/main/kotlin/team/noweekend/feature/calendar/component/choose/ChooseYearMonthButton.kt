package team.noweekend.feature.calendar.component.choose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.graphics.shapes.CornerRounding
import androidx.graphics.shapes.RoundedPolygon
import androidx.graphics.shapes.toPath
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.core.resource.NWKStringResource.YearMonthFormat

@Composable
fun ChooseYearMonthButton(
    year: Int,
    month: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.clickable {
            onClick()
        },
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = stringResource(id = YearMonthFormat, year, month),
            style = NWKTheme.typography.heading4,
            color = NWKTheme.color.Neutral.black,
        )
        val triangleColor = NWKTheme.color.Semantic.Text.body

        Box(
            modifier = Modifier
                .drawWithCache {
                    val left: Float = 5.5.dp.toPx()
                    val leftTop: Float = 9.dp.toPx()
                    val right: Float = 18.5.dp.toPx()
                    val bottom: Float = 17.dp.toPx()
                    val centerX: Float = left + (right - left) / 2

                    // FloatArray로 꼭짓점 좌표 정의
                    val vertices = floatArrayOf(
                        left, leftTop, // 왼쪽 꼭짓점
                        right, leftTop, // 오른쪽 꼭짓점
                        centerX, bottom, // 아래쪽 꼭짓점
                    )

                    val density = this.density
                    val adjustedRadius = 1.5f * density
                    val adjustedSmoothing = 0.6f * density

                    val roundedPolygon = RoundedPolygon(
                        vertices = vertices,
                        rounding = CornerRounding(
                            radius = adjustedRadius, // 피그마의 corner radius 1.5px
                            smoothing = adjustedSmoothing, // 피그마의 corner smoothing 60%
                        ),
                    )
                    val roundedPolygonPath = roundedPolygon.toPath().asComposePath()

                    onDrawBehind {
                        drawPath(
                            path = roundedPolygonPath,
                            color = triangleColor,
                            style = Fill,
                        )
                    }
                }
                .size(24.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewChooseYearMonthButton() {
    NWKTheme {
        ChooseYearMonthButton(
            year = 2023,
            month = 10,
            onClick = {},
        )
    }
}
