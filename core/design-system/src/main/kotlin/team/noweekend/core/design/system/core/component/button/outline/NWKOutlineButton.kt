package team.noweekend.core.design.system.core.component.button.outline

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.noweekend.core.design.system.core.component.button.defaults.Button
import team.noweekend.core.design.system.core.component.button.defaults.ButtonSizeType
import team.noweekend.core.design.system.core.component.button.defaults.NWKButtonDefaults
import team.noweekend.core.design.system.core.component.scaffold.NWKScaffold
import team.noweekend.core.design.system.foundation.theme.NWKTheme

@Composable
fun NWKOutlineButton(
    onClick: () -> Unit,
    text: String,
    size: ButtonSizeType,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    Button(
        onClick = onClick,
        colors = NWKButtonDefaults.blackOutlinedButtonColors(),
        size = size,
        modifier = modifier,
        enabled = true,  // 디자인시스템에 disabled 상태가 정의되어있지 않음
        shape = NWKButtonDefaults.outlinedShape,
        border = NWKButtonDefaults.outlinedButtonBorder(enabled = true),  // 디자인시스템에 disabled 상태가 정의되어있지 않음
        content = {
            Text(
                modifier = Modifier,
                text = text,
                style = NWKTheme.typography.body1.copy(
                    color = NWKTheme.color.Semantic.Text.neutral,
                ),
                maxLines = 1,
            )
        },
    )
}

@Composable
fun NWKOutlineButton(
    onClick: () -> Unit,
    size: ButtonSizeType,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit,
) {
    Button(
        onClick = onClick,
        colors = NWKButtonDefaults.blackOutlinedButtonColors(),
        size = size,
        modifier = modifier,
        enabled = true,  // 디자인시스템에 disabled 상태가 정의되어있지 않음
        shape = NWKButtonDefaults.outlinedShape,
        border = NWKButtonDefaults.outlinedButtonBorder(enabled = true),  // 디자인시스템에 disabled 상태가 정의되어있지 않음
        content = content,
    )
}

@Preview
@Composable
private fun NWKOutlineButtonPreview() {
    NWKTheme {
        NWKScaffold {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                NWKOutlineButton(
                    onClick = {},
                    size = ButtonSizeType.MEDIUM,
                    text = "BTN",
                    modifier = Modifier.fillMaxWidth(),
                )
                NWKOutlineButton(
                    onClick = {},
                    size = ButtonSizeType.EXTRA_LARGE,
                    modifier = Modifier.fillMaxWidth(),
                    content = {
                        Text("asdfasdf")
                    },
                )
            }
        }
    }
}
