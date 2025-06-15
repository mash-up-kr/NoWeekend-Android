package team.noweekend.core.design.system.core.component.tabbar.item

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import team.noweekend.core.design.system.core.component.tabbar.item.defaults.NWKNavigationBarItemColors
import team.noweekend.core.design.system.core.component.tabbar.item.defaults.NWKNavigationBarItemDefaults

@Composable
fun RowScope.NWKNavigationBarItem(
    onClick: () -> Unit,
    isSelected: Boolean,
    icon: @Composable () -> Unit,
    selectedIcon: @Composable () -> Unit,
    label: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    NavigationBarItem(
        selected = isSelected,
        onClick = onClick,
        icon = if (isSelected) selectedIcon else icon,
        modifier = modifier,
        enabled = enabled,
        label = label,
        colors = NWKNavigationBarItemDefaults.colors(),
    )
}
