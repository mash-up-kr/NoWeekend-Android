package team.noweekend.core.design.system.foundation.color.semantics.text

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import team.noweekend.core.design.system.foundation.color.token.NeutralColorToken

internal val LocalTextColor: ProvidableCompositionLocal<TextColor> =
    staticCompositionLocalOf {
        TextColor(
            disabled = NeutralColorToken.NeutralGray700,
            body = NeutralColorToken.NeutralGray800,
            neutral = NeutralColorToken.NeutralGray900,
        )
    }

data class TextColor(
    val disabled: Color,
    val body: Color,
    val neutral: Color,
)
