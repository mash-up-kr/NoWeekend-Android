package team.noweekend.core.design.system.core.component.button.fill

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
import team.noweekend.core.design.system.core.component.button.defaults.BoxButtonType
import team.noweekend.core.design.system.core.component.button.defaults.Button
import team.noweekend.core.design.system.core.component.button.defaults.ButtonSizeType
import team.noweekend.core.design.system.core.component.button.defaults.NWKButtonDefaults
import team.noweekend.core.design.system.core.component.scaffold.NWKScaffold
import team.noweekend.core.design.system.foundation.theme.NWKTheme

@Composable
fun NWKFillButton(
    onClick: () -> Unit,
    text: String,
    type: BoxButtonType,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    val colors = when (type) {
        BoxButtonType.PRIMARY -> NWKButtonDefaults.primaryButtonColors()
        BoxButtonType.BLACK -> NWKButtonDefaults.blackButtonColors()
    }

    Button(
        onClick = onClick,
        colors = colors,
        size = ButtonSizeType.EXTRA_LARGE,
        modifier = modifier,
        enabled = enabled,
        content = {
            Text(
                modifier = Modifier,
                text = text,
                style = NWKTheme.typography.heading6.copy(
                    color = NWKTheme.color.Neutral.white,
                ),
                maxLines = 1,
            )
        },
    )
}

@Composable
fun NWKFillButton(
    onClick: () -> Unit,
    type: BoxButtonType,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit,
) {
    val colors = when (type) {
        BoxButtonType.PRIMARY -> NWKButtonDefaults.primaryButtonColors()
        BoxButtonType.BLACK -> NWKButtonDefaults.blackButtonColors()
    }

    Button(
        onClick = onClick,
        colors = colors,
        size = ButtonSizeType.EXTRA_LARGE,
        modifier = modifier,
        enabled = enabled,
        content = content,
    )
}

@Preview
@Composable
private fun NWKFillButtonPreview() {
    NWKTheme {
        NWKScaffold {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                NWKFillButton(
                    onClick = {},
                    text = "BTN",
                    modifier = Modifier.fillMaxWidth(),
                    type = BoxButtonType.PRIMARY,
                )
                NWKFillButton(
                    onClick = {},
                    text = "BTNBTNBTNBTNBTNBTNBTNBTNBTNBTNBTNBTN",
                    modifier = Modifier.fillMaxWidth(),
                    type = BoxButtonType.BLACK,
                )
                NWKFillButton(
                    onClick = {},
                    modifier = Modifier.fillMaxWidth(),
                    type = BoxButtonType.BLACK,
                    enabled = false,
                    content = {
                        Text("asdfasdf")
                    },
                )
            }
        }
    }
}
