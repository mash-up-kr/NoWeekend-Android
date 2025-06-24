package team.noweekend.feature.main.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.collections.immutable.ImmutableList
import team.noweekend.core.design.system.core.component.tabbar.NavigationBarLayout
import team.noweekend.core.design.system.core.component.tabbar.NWKNavigationBarItem
import team.noweekend.feature.main.MainTab

@Composable
internal fun NWKNavigationBar(
    currentTab: MainTab?,
    tabs: ImmutableList<MainTab>,
    onTabSelected: (MainTab) -> Unit,
    modifier: Modifier = Modifier,
) {
    NavigationBarLayout(
        modifier = modifier.fillMaxWidth(),
    ) {
        tabs.forEach { tab ->
            NWKNavigationBarItem(
                onClick = { onTabSelected(tab) },
                isSelected = tab == currentTab,
                unselectedIconId = tab.unselectedIconResId,
                selectedIconId = tab.selectedIconResId,
                labelId = tab.labelId,
                modifier = Modifier,
            )
        }
    }
}
