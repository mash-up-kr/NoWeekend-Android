package team.noweekend.core.design.system.foundation.theme

import androidx.compose.foundation.LocalIndication
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import team.noweekend.core.design.system.foundation.color.ColorTheme
import team.noweekend.core.design.system.foundation.color.LocalColor
import team.noweekend.core.design.system.foundation.radius.BorderRadius
import team.noweekend.core.design.system.foundation.radius.LocalBorderRadius
import team.noweekend.core.design.system.foundation.spacing.LocalSpacing
import team.noweekend.core.design.system.foundation.spacing.Spacing
import team.noweekend.core.design.system.foundation.theme.ripple.NWKRippleConfiguration

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NWKTheme(
    color: ColorTheme = NWKTheme.color,
    spacing: Spacing = NWKTheme.spacing,
    borderRadius: BorderRadius = NWKTheme.radius,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalColor provides color,
        LocalSpacing provides spacing,
        LocalBorderRadius provides borderRadius,
        LocalIndication provides ripple(),
        LocalRippleConfiguration provides NWKRippleConfiguration,
    ) {
        content()
    }
}

object NWKTheme {
    val color: ColorTheme
        @Composable
        @ReadOnlyComposable
        get() = LocalColor.current

    val spacing: Spacing
        @Composable
        @ReadOnlyComposable
        get() = LocalSpacing.current

    val radius: BorderRadius
        @Composable
        @ReadOnlyComposable
        get() = LocalBorderRadius.current
}
