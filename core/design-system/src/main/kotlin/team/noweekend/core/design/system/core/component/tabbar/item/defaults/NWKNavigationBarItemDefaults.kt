package team.noweekend.core.design.system.core.component.tabbar.item.defaults

import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import team.noweekend.core.design.system.foundation.theme.NWKTheme

internal object NWKNavigationBarItemDefaults {
    @Composable
    fun colors(
        selectedIconColor: Color = NWKTheme.color.Neutral.neutralGray900,
        selectedLabelColor: Color = NWKTheme.color.Neutral.neutralGray900,
        selectedIndicatorColor: Color = Color.Unspecified,
        unselectedIconColor: Color = NWKTheme.color.Neutral.neutralGray700,
        unselectedLabelColor: Color = NWKTheme.color.Neutral.neutralGray700,
        disabledIconColor: Color = Color.Unspecified,
        disabledLabelColor: Color = Color.Unspecified,
    ): NavigationBarItemColors = NavigationBarItemColors(
        selectedIconColor = selectedIconColor,
        selectedTextColor = selectedLabelColor,
        selectedIndicatorColor = selectedIndicatorColor,
        unselectedIconColor = unselectedIconColor,
        unselectedTextColor = unselectedLabelColor,
        disabledIconColor = disabledIconColor,
        disabledTextColor = disabledLabelColor,
    )
}
