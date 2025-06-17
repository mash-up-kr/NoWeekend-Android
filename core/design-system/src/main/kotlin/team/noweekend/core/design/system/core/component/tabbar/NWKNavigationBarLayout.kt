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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun NavigationBarLayout(
    modifier: Modifier = Modifier,
    containerColor: Color = NWKNavigationBarDefaults.containerColor,
    borderColor: Color = NWKNavigationBarDefaults.borderColor,
    windowInsets: WindowInsets = NWKNavigationBarDefaults.windowInsets,
    content: @Composable RowScope.() -> Unit,
) {
    Surface(
        color = containerColor,
        modifier = modifier
            .drawBehind {
                val strokeWidth = 1.dp.toPx()
                drawLine(
                    color = borderColor,
                    start = Offset(0f, 0f),
                    end = Offset(this.size.width, 0f),
                    strokeWidth = strokeWidth,
                )
            },
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .windowInsetsPadding(windowInsets)
                .defaultMinSize(minHeight = NWKNavigationBarDefaults.NavigationBarHeight)
                .selectableGroup(),
            horizontalArrangement = Arrangement.SpaceAround,
            content = content,
        )
    }
}
