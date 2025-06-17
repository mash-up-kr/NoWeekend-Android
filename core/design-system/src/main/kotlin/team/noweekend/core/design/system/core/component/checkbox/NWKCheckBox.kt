package team.noweekend.core.design.system.core.component.checkbox

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.noweekend.core.design.system.foundation.theme.NWKTheme


object NWKCheckBox {


    @Composable
    private fun Frame(
        modifier: Modifier = Modifier,
        checkBoxState: NWKCheckBoxState = NWKCheckBoxState.UNCHECKED,
        checkBoxColors: NWKCheckBoxColors = NWKCheckBoxColorsDefault.basicColors,
        onClick: () -> Unit = {},

        ) {
        val foregroundColor = checkBoxColors.foregroundColor(checkBoxState = checkBoxState)
        val backgroundColor = checkBoxColors.backgroundColor(checkBoxState = checkBoxState)
        val borderColor = checkBoxColors.borderColor(checkBoxState = checkBoxState)
        val selected = checkBoxState == NWKCheckBoxState.CHECKED

        Surface(
            modifier = modifier
                .padding(3.dp)
                .semantics {
                    Role.Checkbox
                },
            selected = selected,
            onClick = onClick,
            border = BorderStroke(
                width = 2.dp,
                color = borderColor.value,
            ),
            shape = NWKTheme.radius.borderRadius50,
        ) {
            Canvas(
                modifier = Modifier
                    .size(18.dp)
                    .clip(NWKTheme.radius.borderRadius50),
            ) {

                val dpToPx = density

                drawRect(
                    color = backgroundColor.value,
                    size = size,
                )
                if (selected) {
                    val path = Path()
                    path.apply {
                        moveTo(x = 4.5f * dpToPx, y = 9f * dpToPx)
                        lineTo(x = 7f * dpToPx, y = 12.5f * dpToPx)
                        lineTo(x = 13.5f * dpToPx, y = 4.5f * dpToPx)
                    }

                    drawPath(
                        path = path,
                        color = foregroundColor.value,
                        style = Stroke(width = 2f * dpToPx, cap = StrokeCap.Round, join = StrokeJoin.Round),
                    )
                }
            }
        }
    }

    @Composable
    fun Basic(
        modifier: Modifier = Modifier,
        checkBoxState: NWKCheckBoxState = NWKCheckBoxState.UNCHECKED,
        onClick: () -> Unit = {},
    ) {
        Frame(
            modifier = modifier,
            checkBoxState = checkBoxState,
            onClick = onClick,
        )
    }
}

@Preview
@Composable
private fun PreviewBasicCheckBox() {
    NWKTheme {
        var checkBoxState by remember { mutableStateOf(NWKCheckBoxState.UNCHECKED) }
        NWKCheckBox.Basic(
            checkBoxState = checkBoxState,
            onClick = {
                checkBoxState = if (checkBoxState == NWKCheckBoxState.CHECKED) {
                    NWKCheckBoxState.UNCHECKED
                } else {
                    NWKCheckBoxState.CHECKED
                }
            },
        )
    }
}

