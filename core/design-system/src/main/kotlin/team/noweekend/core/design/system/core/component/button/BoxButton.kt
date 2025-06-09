package team.noweekend.core.design.system.core.component.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import team.noweekend.core.design.system.core.component.button.defaults.Button
import team.noweekend.core.design.system.core.component.button.defaults.NWKButtonDefaults
import team.noweekend.core.design.system.core.component.scaffold.NWKScaffold
import team.noweekend.core.design.system.foundation.theme.NWKTheme

enum class BoxButtonType {
    DEFAULT,
    ACTIVE,
    ;
}

/**
 * Catalog 예시를 위한 임시 컴포넌트
 */
@Composable
fun BoxButton(
    onClick: () -> Unit,
    type: BoxButtonType,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    border: BorderStroke? = null,
    content: @Composable RowScope.() -> Unit,
) {
    val colors = when (type) {
        BoxButtonType.DEFAULT -> NWKButtonDefaults.defaultButtonColors()
        BoxButtonType.ACTIVE -> NWKButtonDefaults.activeButtonColors()
    }

    Button(
        onClick = onClick,
        colors = colors,
        modifier = modifier,
        enabled = enabled,
        border = border,
        content = content,
    )
}

@Preview
@Composable
private fun BoxButtonPreview() {
    NWKTheme {
        NWKScaffold {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
            ) {
                BoxButton(
                    onClick = {},
                    type = BoxButtonType.ACTIVE,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("HIHI")
                }
                BoxButton(
                    onClick = {},
                    type = BoxButtonType.DEFAULT
                ) {
                    Text("HIHI")
                }
            }
        }
    }
}
