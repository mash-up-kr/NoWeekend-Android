package team.noweekend.core.design.system.core.component.tabbar.item.defaults

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import team.noweekend.core.design.system.foundation.theme.NWKTheme

internal object NWKNavigationBarItemDefaults {
    @Composable
    fun colors(
        selectedIconColor: Color = NWKTheme.color.Neutral.neutralGray900,
        selectedLabelColor: Color = NWKTheme.color.Neutral.neutralGray900,
        unselectedIconColor: Color = NWKTheme.color.Neutral.neutralGray700,
        unselectedLabelColor: Color = NWKTheme.color.Neutral.neutralGray700,
        disabledIconColor: Color = Color.Unspecified,
        disabledLabelColor: Color = Color.Unspecified,
    ): NWKNavigationBarItemColors = NWKNavigationBarItemColors(
        selectedIconColor = selectedIconColor,
        selectedLabelColor = selectedLabelColor,
        unselectedIconColor = unselectedIconColor,
        unselectedLabelColor = unselectedLabelColor,
        disabledIconColor = disabledIconColor,
        disabledLabelColor = disabledLabelColor,
    )
}
