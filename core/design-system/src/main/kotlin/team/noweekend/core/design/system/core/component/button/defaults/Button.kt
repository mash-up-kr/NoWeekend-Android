package team.noweekend.core.design.system.core.component.button.defaults

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics

@Composable
internal fun Button(
    onClick: () -> Unit,
    colors: NWKButtonColors,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: CornerBasedShape = NWKButtonDefaults.shape,
    border: BorderStroke? = null,
    content: @Composable RowScope.() -> Unit,
) {
    val contentColor: Color by colors.contentColor(enabled)
    val backgroundColor: Color by colors.backgroundColor(enabled)

    Surface(
        modifier = modifier
            .semantics { role = Role.Button }
            .clickable(
                enabled = enabled,
                onClick = onClick,
            ),
        shape = shape,
        color = backgroundColor,
        contentColor = contentColor,
        border = border,
    ) {
        Row(
            modifier = Modifier
                // horizontal, vertical padding 지정 시 defaultMinSize() 제거
                .defaultMinSize(
                    minWidth = NWKButtonDefaults.MinWidth,
                    minHeight = NWKButtonDefaults.MinHeight,
                )
                .padding(NWKButtonDefaults.ContentPadding),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            content = content,
        )
    }
}
