package team.noweekend.core.common.ui.chip

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import team.noweekend.core.common.ui.chip.defaults.ChipColors
import team.noweekend.core.common.ui.chip.defaults.ChipDefaults
import team.noweekend.core.design.system.foundation.theme.NWKTheme

@Composable
fun Chip(
    text: String,
    isSelected: Boolean,
    onChipSelect: () -> Unit,
    modifier: Modifier = Modifier,
    colors: ChipColors = ChipDefaults.colors(),
) {
    val contentColor: Color by colors.contentColor(isSelected)
    val borderStroke: BorderStroke by ChipDefaults.borderStroke(isSelected)

    Surface(
        modifier = modifier
            .semantics { role = Role.RadioButton }
            .clip(NWKTheme.radius.borderRadius400)
            .clickable(
                enabled = true,
                onClick = onChipSelect,
            ),
        shape = ChipDefaults.shape,
        color = ChipDefaults.backgroundColor,
        contentColor = contentColor,
        border = borderStroke,
    ) {
        Text(
            modifier = Modifier
                .wrapContentWidth()
                .padding(ChipDefaults.contentPadding),
            text = text,
            style = NWKTheme.typography.subTitle1.copy(
                color = contentColor,
            ),
            maxLines = 1,
            textAlign = TextAlign.Center,
        )
    }
}

@Preview
@Composable
private fun ChipPreview() {
    NWKTheme {
        Chip(
            text = "포카칩",
            isSelected = true,
            onChipSelect = {},
        )
    }
}
