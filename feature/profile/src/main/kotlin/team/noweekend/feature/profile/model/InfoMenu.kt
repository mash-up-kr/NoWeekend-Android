package team.noweekend.feature.profile.model

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import team.noweekend.core.common.ui.todo.model.TodoType
import team.noweekend.core.design.system.core.component.toggle.Toggle
import team.noweekend.core.design.system.core.component.toggle.ToggleState
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.core.resource.NWKStringResource.InfoMenuDefaultCategoryTitle
import team.noweekend.core.resource.NWKStringResource.InfoMenuManageVacationTitle
import team.noweekend.core.resource.NWKStringResource.InfoMenuSettingAlarmTitle


@Stable
sealed interface InfoMenu : Menu {

    data class ManageVacation(
        override val title: Int = InfoMenuManageVacationTitle,
    ) : InfoMenu

    data class DefaultCategory(
        override val title: Int = InfoMenuDefaultCategoryTitle,
        val category: TodoType,
        override val content: @Composable () -> Unit = {
            Text(
                modifier = Modifier.padding(end = 20.dp),
                text = stringResource(id = title),
                color = category.color,
                style = NWKTheme.typography.body1.copy(
                    fontWeight = FontWeight.Medium,
                ),
            )
        },
    ) : InfoMenu

    /**
     * Todo 나중에 구현합니다.
     */
    data class SettingAlarm(
        override val title: Int = InfoMenuSettingAlarmTitle,
        val isEnabled: Boolean,
        override val content: @Composable () -> Unit = {
            var toggleState by remember {
                mutableStateOf(
                    if (isEnabled) ToggleState.ON else ToggleState.OFF,
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
        },
    ) : InfoMenu

    companion object {
        val infoMenuList: ImmutableList<InfoMenu> = persistentListOf(
            ManageVacation(),
            DefaultCategory(category = TodoType.Personal()),
        )
    }
}
