package team.noweekend.feature.calendar.component.choose

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
        Canvas(
            modifier = Modifier.size(24.dp),
        ) {
            val width = size.width

            val left: Float = 5.5.dp.toPx()
            val leftTop: Float = 9.dp.toPx()
            val right: Float = 18.5.dp.toPx()
            val bottom: Float = 18.dp.toPx()

            val rect = Rect(left, leftTop, right, bottom)

            val path = Path().apply {
                moveTo(left, leftTop)
                lineTo(right, leftTop)
                lineTo(width / 2, bottom)
                close()
            }

            drawIntoCanvas { canvas->
                canvas.drawOutline(
                    outline = Outline.Generic(path),
                    paint = Paint().apply {
                        color = triangleColor
                        pathEffect = PathEffect.cornerPathEffect(rect.maxDimension / 3f)
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewChooseYearMonthButton() {
    NWKTheme {
        ChooseYearMonthButton(
            year = 2023,
            month = 10,
            onClick = {}
        )
    }
}
