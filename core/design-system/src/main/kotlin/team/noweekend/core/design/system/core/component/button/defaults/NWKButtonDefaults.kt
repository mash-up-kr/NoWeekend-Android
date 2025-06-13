package team.noweekend.core.design.system.core.component.button.defaults

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import team.noweekend.core.design.system.foundation.theme.NWKTheme

internal object NWKButtonDefaults {

    val shape: CornerBasedShape
        @Composable get() = NWKTheme.radius.borderRadius500

    val outlinedShape: CornerBasedShape
        @Composable get() = NWKTheme.radius.borderRadius400

    private val outlinedBorderColor: Color
        @Composable get() = NWKTheme.color.Semantic.Border.border02

    fun getContentPadding(size: ButtonSizeType): PaddingValues =
        PaddingValues(
            horizontal = size.horizontalPadding,
            vertical = size.verticalPadding,
        )

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
        disabledBackgroundColor: Color = NWKTheme.color.Neutral.neutralGray700,
        disabledContentColor: Color = NWKTheme.color.Neutral.white,
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


