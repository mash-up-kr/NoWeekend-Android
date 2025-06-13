package team.noweekend.core.design.system.core.component.tabbar

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.systemBars
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import team.noweekend.core.design.system.foundation.theme.NWKTheme

object NWKNavigationBarDefaults {
    val NavigationBarHeight: Dp = 84.dp
    val NavigationBarTopPadding: Dp = 8.dp

    val containerColor: Color
        @Composable
        get() = NWKTheme.color.Neutral.white

    val windowInsets: WindowInsets
        @Composable
        get() = WindowInsets.systemBars.only(
            WindowInsetsSides.Horizontal + WindowInsetsSides.Bottom,
        )
}
