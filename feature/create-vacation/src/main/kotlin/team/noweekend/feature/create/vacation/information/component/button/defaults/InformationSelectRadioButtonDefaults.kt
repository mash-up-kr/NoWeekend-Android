package team.noweekend.feature.create.vacation.information.component.button.defaults

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import team.noweekend.core.design.system.foundation.theme.NWKTheme

object InformationSelectRadioButtonDefaults {

    val shape: CornerBasedShape
        @Composable get() = NWKTheme.radius.borderRadius400

    val contentPadding: PaddingValues
        @Composable get() = PaddingValues(
            horizontal = NWKTheme.spacing.space150,
            vertical = NWKTheme.spacing.space100,
        )

    val backgroundColor: Color
        @Composable get() = NWKTheme.color.Neutral.white

    @Composable
    fun borderStroke(selected: Boolean): State<BorderStroke> {
        return rememberUpdatedState(
            newValue = BorderStroke(
                width = 1.dp,
                color = colors().borderColor(selected).value,
            ),
        )
    }

    @Composable
    fun colors(
        contentColor: Color = NWKTheme.color.Neutral.black,
        borderColor: Color = NWKTheme.color.Toast.toast500,
        unselectedContentColor: Color = NWKTheme.color.Semantic.Text.disabled,
        unselectedBorderColor: Color = NWKTheme.color.Semantic.Border.border01,
    ): InformationSelectRadioButtonColors {
        return InformationSelectRadioButtonColors(
            contentColor = contentColor,
            borderColor = borderColor,
            unselectedContentColor = unselectedContentColor,
            unselectedBorderColor = unselectedBorderColor,
        )
    }
}
