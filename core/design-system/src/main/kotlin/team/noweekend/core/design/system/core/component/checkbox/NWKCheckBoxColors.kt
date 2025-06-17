package team.noweekend.core.design.system.core.component.checkbox

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.graphics.Color
import team.noweekend.core.design.system.foundation.theme.NWKTheme

@Stable
interface NWKCheckBoxColors {
    @Composable
    fun borderColor(checkBoxState: NWKCheckBoxState): State<Color>

    @Composable
    fun foregroundColor(checkBoxState: NWKCheckBoxState): State<Color>

    @Composable
    fun backgroundColor(checkBoxState: NWKCheckBoxState): State<Color>
}


object NWKCheckBoxColorsDefault {
    val basicColors = object : NWKCheckBoxColors {
        @Composable
        override fun borderColor(checkBoxState: NWKCheckBoxState): State<Color> {
            return rememberUpdatedState(
                if (checkBoxState == NWKCheckBoxState.CHECKED) {
                    NWKTheme.color.Neutral.neutralGray900
                } else {
                    NWKTheme.color.Neutral.neutralGray700
                },
            )
        }

        @Composable
        override fun foregroundColor(checkBoxState: NWKCheckBoxState): State<Color> {
            return rememberUpdatedState(NWKTheme.color.Neutral.white)
        }

        @Composable
        override fun backgroundColor(checkBoxState: NWKCheckBoxState): State<Color> {
            return rememberUpdatedState(
                if (checkBoxState == NWKCheckBoxState.CHECKED) {
                    NWKTheme.color.Neutral.neutralGray900
                } else {
                    NWKTheme.color.Neutral.white
                },
            )
        }
    }
}
