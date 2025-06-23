package team.noweekend.core.design.system.foundation.theme.ripple

import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RippleConfiguration
import androidx.compose.ui.graphics.Color
import team.noweekend.core.design.system.foundation.color.token.NeutralColorToken
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.core.design.system.foundation.theme.ripple.token.RippleStateTokens

@OptIn(ExperimentalMaterial3Api::class)
internal val NWKRippleConfiguration: RippleConfiguration = RippleConfiguration(
    color = NeutralColorToken.Black,
    rippleAlpha = provideRippleAlpha(),
)

private fun provideRippleAlpha(): RippleAlpha =
    RippleAlpha(
        pressedAlpha = RippleStateTokens.PressedStateLayerOpacity,
        focusedAlpha = RippleStateTokens.FocusStateLayerOpacity,
        draggedAlpha = RippleStateTokens.DraggedStateLayerOpacity,
        hoveredAlpha = RippleStateTokens.HoverStateLayerOpacity,
    )

