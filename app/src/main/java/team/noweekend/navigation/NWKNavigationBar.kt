package team.noweekend.navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import team.noweekend.core.design.system.core.component.tabbar.NavigationBarLayout
import team.noweekend.core.design.system.core.component.tabbar.item.NWKNavigationBarItem

@Composable
internal fun NWKNavigationBar(
    navigateToTargetTab: (NavigationTab) -> Unit,
    currentTab: NavigationTab?,
    modifier: Modifier = Modifier,
    navigationTabs: List<NavigationTab> = NavigationTab.entries,
) {
    NavigationBarLayout(
        modifier = modifier.fillMaxWidth(),
    ) {
        navigationTabs.forEach { tab ->
            NWKNavigationBarItem(
                onClick = { navigateToTargetTab(tab) },
                isSelected = tab == currentTab,
                unselectedIconId = tab.unselectedIconResId,
                selectedIconId = tab.selectedIconResId,
                labelId = tab.labelId,
                modifier = Modifier,
            )
        }
    }
}
