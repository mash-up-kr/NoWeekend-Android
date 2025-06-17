package team.noweekend.feature.main.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.collections.immutable.ImmutableList
import team.noweekend.core.design.system.core.component.tabbar.NavigationBarLayout
import team.noweekend.core.design.system.core.component.tabbar.item.NWKNavigationBarItem
import team.noweekend.feature.main.MainTab

@Composable
internal fun NWKNavigationBar(
    navigateToTargetTab: (MainTab) -> Unit,
    currentTab: MainTab?,
    topLevelDestinations: ImmutableList<MainTab>,
    modifier: Modifier = Modifier,
) {
    NavigationBarLayout(
        modifier = modifier.fillMaxWidth(),
    ) {
        topLevelDestinations.forEach { tab ->
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
