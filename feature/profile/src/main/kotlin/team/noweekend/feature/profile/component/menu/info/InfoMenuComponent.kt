package team.noweekend.feature.profile.component.menu.info

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import team.noweekend.core.common.android.extension.fillMaxWidthOfScreen
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.feature.profile.component.menu.model.InfoMenu
import team.noweekend.core.design.system.core.component.toggle.Toggle
import team.noweekend.core.design.system.core.component.toggle.ToggleState

@Composable
fun InfoMenuComponent(
    menu: InfoMenu,
    onClickMenuItem: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidthOfScreen()
            .height(56.dp)
            .clickable(onClick = onClickMenuItem),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            modifier = Modifier.padding(start = 20.dp),
            text = stringResource(id = menu.title),
            style = NWKTheme.typography.body1.copy(
                fontWeight = FontWeight.Medium,
            ),
            color = NWKTheme.color.Semantic.Text.neutral,
        )
        when (menu) {

            is InfoMenu.DefaultCategory -> {
                Text(
                    modifier = Modifier.padding(end = 20.dp),
                    text = stringResource(id = menu.category.title),
                    color = menu.category.color,
                    style = NWKTheme.typography.body1.copy(
                        fontWeight = FontWeight.Medium,
                    ),
                )
            }

            is InfoMenu.SettingAlarm -> {
                var toggleState by remember {
                    mutableStateOf(
                        if (menu.isEnabled) ToggleState.ON else ToggleState.OFF,
                    )
                }
                Toggle(
                    modifier = Modifier.padding(end = 20.dp),
                    toggleState = toggleState,
                    onClickToggle = {
                        toggleState = if (toggleState == ToggleState.ON) ToggleState.OFF else ToggleState.ON
                    },
                    onToggleStateChanged = { isOn ->
                        toggleState = if (isOn) ToggleState.ON else ToggleState.OFF
                    },
                )
            }

            else -> {}

        }
    }
}
