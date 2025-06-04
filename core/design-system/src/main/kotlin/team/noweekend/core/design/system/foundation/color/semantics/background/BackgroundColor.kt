package team.noweekend.core.design.system.foundation.color.semantics.background

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import team.noweekend.core.design.system.foundation.color.token.NeutralColorToken

internal val LocalBackgroundColor: ProvidableCompositionLocal<BackgroundColor> =
    staticCompositionLocalOf {
        BackgroundColor(
            normal = NeutralColorToken.White,
            alternative01 = NeutralColorToken.NeutralGray100,
        )
    }

data class BackgroundColor(
    val normal: Color,
    val alternative01: Color,
)
