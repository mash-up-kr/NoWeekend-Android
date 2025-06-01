package team.noweekend.core.design.system.foundation.radius

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.runtime.staticCompositionLocalOf
import team.noweekend.core.design.system.foundation.radius.token.BorderRadiusToken

internal val LocalBorderRadius = staticCompositionLocalOf {
    BorderRadius(
        borderRadius0 = BorderRadiusToken.BorderRadius0,
        borderRadius50 = BorderRadiusToken.BorderRadius50,
        borderRadius100 = BorderRadiusToken.BorderRadius100,
        borderRadius200 = BorderRadiusToken.BorderRadius200,
        borderRadius300 = BorderRadiusToken.BorderRadius300,
        borderRadius400 = BorderRadiusToken.BorderRadius400,
        borderRadius500 = BorderRadiusToken.BorderRadius500,
        borderRadius600 = BorderRadiusToken.BorderRadius600,
        borderRadius700 = BorderRadiusToken.BorderRadius700,
    )
}

data class BorderRadius(
    val borderRadius0: CornerBasedShape,
    val borderRadius50: CornerBasedShape,
    val borderRadius100: CornerBasedShape,
    val borderRadius200: CornerBasedShape,
    val borderRadius300: CornerBasedShape,
    val borderRadius400: CornerBasedShape,
    val borderRadius500: CornerBasedShape,
    val borderRadius600: CornerBasedShape,
    val borderRadius700: CornerBasedShape,
)
