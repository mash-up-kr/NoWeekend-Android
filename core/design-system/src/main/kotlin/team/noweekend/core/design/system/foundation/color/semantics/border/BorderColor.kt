package team.noweekend.core.design.system.foundation.color.semantics.border

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import team.noweekend.core.design.system.foundation.color.token.NeutralColorToken

internal val LocalBorderColor: ProvidableCompositionLocal<BorderColor> =
    staticCompositionLocalOf {
        BorderColor(
            border01 = NeutralColorToken.NeutralGray200,
            border02 = NeutralColorToken.NeutralGray300,
        )
    }

data class BorderColor(
    val border01: Color,
    val border02: Color,
)
