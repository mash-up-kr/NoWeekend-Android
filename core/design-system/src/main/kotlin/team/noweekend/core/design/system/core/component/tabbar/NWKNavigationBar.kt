package team.noweekend.core.design.system.core.component.tabbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import team.noweekend.core.design.system.core.component.scaffold.NWKScaffold
import team.noweekend.core.design.system.foundation.theme.NWKTheme

@Composable
fun NWKNavigationBar(
    modifier: Modifier = Modifier,
    containerColor: Color = NWKNavigationBarDefaults.containerColor,
    windowInsets: WindowInsets = NWKNavigationBarDefaults.windowInsets,
    content: @Composable RowScope.() -> Unit,
) {
    Surface(
        color = containerColor,
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = NWKNavigationBarDefaults.NavigationBarTopPadding)
                .windowInsetsPadding(windowInsets)
                .defaultMinSize(minHeight = NWKNavigationBarDefaults.NavigationBarHeight)
                .selectableGroup(),
            horizontalArrangement = Arrangement.SpaceAround,
            content = content,
        )
    }
}

@Preview
@Composable
private fun NWKNavigationBarPreview() {
    NWKTheme {
        NWKScaffold(
            bottomBar = {
                NWKNavigationBar {

                }
            },
        ) { }
    }
}
