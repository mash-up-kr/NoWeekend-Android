package team.noweekend.core.design.system.foundation.color

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf

internal val LocalColor = staticCompositionLocalOf { ColorTheme }

object ColorTheme {
    val Neutral: NeutralColor
        @Composable
        @ReadOnlyComposable
        get() = LocalNeutralColor.current

    val Toast: ToastColor
        @Composable
        @ReadOnlyComposable
        get() = LocalToastColor.current
}
