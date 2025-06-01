package team.noweekend.core.design.system.foundation.spacing

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import team.noweekend.core.design.system.foundation.spacing.token.SpaceToken

internal val LocalSpacing = staticCompositionLocalOf {
    Spacing(
        space0 = SpaceToken.Space0,
        space25 = SpaceToken.Space25,
        space50 = SpaceToken.Space50,
        space75 = SpaceToken.Space75,
        space100 = SpaceToken.Space100,
        space150 = SpaceToken.Space150,
        space175 = SpaceToken.Space175,
        space200 = SpaceToken.Space200,
        space300 = SpaceToken.Space300,
        space400 = SpaceToken.Space400,
        space500 = SpaceToken.Space500,
        space600 = SpaceToken.Space600,
        space700 = SpaceToken.Space700,
        space800 = SpaceToken.Space800,
        space900 = SpaceToken.Space900,
    )
}

data class Spacing(
    val space0: Dp,
    val space25: Dp,
    val space50: Dp,
    val space75: Dp,
    val space100: Dp,
    val space150: Dp,
    val space175: Dp,
    val space200: Dp,
    val space300: Dp,
    val space400: Dp,
    val space500: Dp,
    val space600: Dp,
    val space700: Dp,
    val space800: Dp,
    val space900: Dp,
)
