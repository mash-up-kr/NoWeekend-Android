package team.noweekend.core.design.system.core.component.control.page.defaults

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import team.noweekend.core.design.system.foundation.theme.NWKTheme

internal object NWKPageControlDefaults {
    val indicatorShape: CornerBasedShape
        @Composable get() = RoundedCornerShape(50.dp)

    val indicatorGap: Dp
        @Composable get() = NWKTheme.spacing.space100

    @Composable
    fun indicatorColor(
        selectedBackgroundColor: Color = NWKTheme.color.Toast.toast500,
        unSelectedBackgroundColor: Color = NWKTheme.color.Neutral.neutralGray600,
    ): NWKPageControlColors {
        return NWKPageControlColors(
            selectedBackgroundColor = selectedBackgroundColor,
            unSelectedBackgroundColor = unSelectedBackgroundColor,
        )
    }

    enum class IndicatorType(
        val width: Dp,
        val height: Dp,
    ) {
        DEFAULT(
            width = 8.dp,
            height = 8.dp
        ),
        SELECTED(
            width = 16.dp,
            height = 8.dp,
        ),
        ;
    }
}
