package team.noweekend.core.design.system.core.component.tabbar.defaults

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
    val NavigationBarHeight: Dp = 70.dp
    val NavigationBarVerticalPadding: Dp = 12.dp

    val borderColor: Color
        @Composable
        get() = NWKTheme.color.Semantic.Border.border01

    val containerColor: Color
        @Composable
        get() = NWKTheme.color.Neutral.white

    val windowInsets: WindowInsets
        @Composable
        get() = WindowInsets.systemBars.only(
            WindowInsetsSides.Horizontal + WindowInsetsSides.Bottom,
        )
}
