package team.noweekend.core.design.system.core.component.control.page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import team.noweekend.core.design.system.core.component.control.page.defaults.NWKPageControlColors
import team.noweekend.core.design.system.core.component.control.page.defaults.NWKPageControlDefaults
import team.noweekend.core.design.system.foundation.theme.NWKTheme

@Composable
fun NWKPageControl(
    pageSize: Int,
    currentPosition: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = NWKTheme.spacing.space150,
                vertical = NWKTheme.spacing.space100,
            ),
        horizontalArrangement = Arrangement.spacedBy(
            space = NWKPageControlDefaults.indicatorGap,
            alignment = Alignment.CenterHorizontally,
        ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        repeat(pageSize) { page ->
            NWKDotIndicator(
                selected = page == currentPosition,
                modifier = Modifier,
            )
        }
    }
}

@Composable
private fun NWKDotIndicator(
    selected: Boolean,
    modifier: Modifier = Modifier,
    colors: NWKPageControlColors = NWKPageControlDefaults.indicatorColor(),
    shape: CornerBasedShape = NWKPageControlDefaults.indicatorShape,
) {
    val backgroundColor: Color by colors.backgroundColor(selected)
    val shapeModifier = when {
        selected -> {
            modifier.size(
                width = NWKPageControlDefaults.IndicatorType.SELECTED.width,
                height = NWKPageControlDefaults.IndicatorType.SELECTED.height,
            )
        }

        else -> {
            modifier.size(
                width = NWKPageControlDefaults.IndicatorType.DEFAULT.width,
                height = NWKPageControlDefaults.IndicatorType.DEFAULT.height,
            )
        }
    }

    Box(
        modifier = modifier
            .then(shapeModifier)
            .clip(shape = shape)
            .background(color = backgroundColor),
    )
}

@Preview
@Composable
private fun NWKPageControlPreview() {
    NWKTheme {
        NWKPageControl(
            modifier = Modifier.background(NWKTheme.color.Neutral.white),
            pageSize = 4,
            currentPosition = 1,
        )
    }
}
