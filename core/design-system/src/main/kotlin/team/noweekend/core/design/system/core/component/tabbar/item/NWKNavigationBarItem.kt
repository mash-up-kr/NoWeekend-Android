package team.noweekend.core.design.system.core.component.tabbar.item

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import team.noweekend.core.design.system.core.component.tabbar.item.defaults.NWKNavigationBarItemDefaults
import team.noweekend.core.design.system.foundation.theme.NWKTheme

@Composable
fun RowScope.NWKNavigationBarItem(
    onClick: () -> Unit,
    isSelected: Boolean,
    unselectedIconId: Int,
    selectedIconId: Int,
    labelId: Int,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: NavigationBarItemColors = NWKNavigationBarItemDefaults.colors(),
) {
    NavigationBarItem(
        selected = isSelected,
        onClick = onClick,
        icon = {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(if (isSelected) selectedIconId else unselectedIconId),
                tint = if (isSelected) colors.selectedIconColor else colors.unselectedIconColor,
                contentDescription = null,
            )
        },
        modifier = modifier,
        enabled = enabled,
        label = {
            Text(
                text = stringResource(labelId),
                style = NWKTheme.typography.body3.copy(
                    fontWeight = if (isSelected) FontWeight.W700 else FontWeight.W600,
                    color = if (isSelected) colors.selectedTextColor else colors.unselectedTextColor,
                ),
            )
        },
        colors = NavigationBarItemDefaults.colors(indicatorColor = Color.Transparent),
    )
}
