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
    fun borderColor(isChecked: Boolean): State<Color>

    @Composable
    fun foregroundColor(isChecked: Boolean): State<Color>

    @Composable
    fun backgroundColor(isChecked: Boolean): State<Color>
}


object NWKCheckBoxColorsDefault {
    val colors = object : NWKCheckBoxColors {

        /**
         * 체크 박스 보더 색상
         */
        @Composable
        override fun borderColor(isChecked: Boolean): State<Color> {
            return rememberUpdatedState(
                if (isChecked) {
                    NWKTheme.color.Neutral.neutralGray900
                } else {
                    NWKTheme.color.Neutral.neutralGray700
                },
            )
        }

        /**
         * 체크 표시 색상
         */
        @Composable
        override fun foregroundColor(isChecked: Boolean): State<Color> {
            return rememberUpdatedState(NWKTheme.color.Neutral.white)
        }

        /**
         * 체크 박스 배경 색상
         */
        @Composable
        override fun backgroundColor(isChecked: Boolean): State<Color> {
            return rememberUpdatedState(
                if (isChecked) {
                    NWKTheme.color.Neutral.neutralGray900
                } else {
                    NWKTheme.color.Neutral.white
                },
            )
        }
    }
}
