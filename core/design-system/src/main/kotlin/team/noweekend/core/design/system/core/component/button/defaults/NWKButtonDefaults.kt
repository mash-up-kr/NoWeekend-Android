package team.noweekend.core.design.system.core.component.button.defaults

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import team.noweekend.core.design.system.foundation.theme.NWKTheme

internal object NWKButtonDefaults {
    private val ButtonHorizontalPadding: Dp = 24.dp
    private val ButtonVerticalPadding: Dp = 8.dp

    val ContentPadding: PaddingValues =
        PaddingValues(
            horizontal = ButtonHorizontalPadding,
            vertical = ButtonVerticalPadding,
        )

    val MinWidth = 58.dp
    val MinHeight = 60.dp

    val shape: CornerBasedShape
        @Composable get() = NWKTheme.radius.borderRadius500

    val outlinedShape: CornerBasedShape
        @Composable get() = NWKTheme.radius.borderRadius400

    @Composable
    fun outlinedButtonBorder(enabled: Boolean = true): BorderStroke =
        BorderStroke(
            width = 1.dp,
            color =
            if (enabled) {
                outlinedBorderColor
            } else {
                outlinedBorderColor
            },
        )

    private val outlinedBorderColor: Color
        @Composable get() = NWKTheme.color.Semantic.Border.border02

    @Composable
    fun primaryButtonColors(
        backgroundColor: Color = NWKTheme.color.Toast.toast500,
        contentColor: Color = NWKTheme.color.Neutral.white,
        disabledBackgroundColor: Color = Color.Unspecified,
        disabledContentColor: Color = Color.Unspecified,
    ): NWKButtonColors {
        return NWKButtonColors(
            backgroundColor = backgroundColor,
            contentColor = contentColor,
            disabledBackgroundColor = disabledBackgroundColor,
            disabledContentColor = disabledContentColor,
        )
    }

    @Composable
    fun blackButtonColors(
        backgroundColor: Color = NWKTheme.color.Neutral.black,
        contentColor: Color = NWKTheme.color.Neutral.white,
        disabledBackgroundColor: Color = Color.Unspecified,
        disabledContentColor: Color = Color.Unspecified,
    ): NWKButtonColors {
        return NWKButtonColors(
            backgroundColor = backgroundColor,
            contentColor = contentColor,
            disabledBackgroundColor = disabledBackgroundColor,
            disabledContentColor = disabledContentColor,
        )
    }

    @Composable
    fun blackOutlinedButtonColors(
        backgroundColor: Color = NWKTheme.color.Neutral.white,
        contentColor: Color = NWKTheme.color.Neutral.black,
        disabledBackgroundColor: Color = Color.Unspecified,
        disabledContentColor: Color = Color.Unspecified,
    ): NWKButtonColors {
        return NWKButtonColors(
            backgroundColor = backgroundColor,
            contentColor = contentColor,
            disabledBackgroundColor = disabledBackgroundColor,
            disabledContentColor = disabledContentColor,
        )
    }
}


