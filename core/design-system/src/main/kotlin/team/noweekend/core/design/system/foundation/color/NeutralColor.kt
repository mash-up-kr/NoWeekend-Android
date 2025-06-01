package team.noweekend.core.design.system.foundation.color

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import team.noweekend.core.design.system.foundation.color.token.NeutralColorToken

internal val LocalNeutralColor = staticCompositionLocalOf {
    NeutralColor(
        white = NeutralColorToken.White,
        neutralGray100 = NeutralColorToken.NeutralGray100,
        neutralGray200 = NeutralColorToken.NeutralGray200,
        neutralGray300 = NeutralColorToken.NeutralGray300,
        neutralGray400 = NeutralColorToken.NeutralGray400,
        neutralGray500 = NeutralColorToken.NeutralGray500,
        neutralGray600 = NeutralColorToken.NeutralGray600,
        neutralGray700 = NeutralColorToken.NeutralGray700,
        neutralGray800 = NeutralColorToken.NeutralGray800,
        neutralGray900 = NeutralColorToken.NeutralGray900,
        black = NeutralColorToken.Black,
    )
}

data class NeutralColor(
    val white: Color,
    val neutralGray100: Color,
    val neutralGray200: Color,
    val neutralGray300: Color,
    val neutralGray400: Color,
    val neutralGray500: Color,
    val neutralGray600: Color,
    val neutralGray700: Color,
    val neutralGray800: Color,
    val neutralGray900: Color,
    val black: Color,
)

