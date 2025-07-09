package team.noweekend.feature.profile.component.menu.model

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import team.noweekend.core.common.ui.todo.model.TodoType
import team.noweekend.core.resource.NWKStringResource.InfoMenuDefaultCategoryTitle
import team.noweekend.core.resource.NWKStringResource.InfoMenuManageVacationTitle
import team.noweekend.core.resource.NWKStringResource.InfoMenuSettingAlarmTitle


sealed interface InfoMenu : Menu {

    data class ManageVacation(
        override val title: Int = InfoMenuManageVacationTitle,
    ) : InfoMenu

    data class DefaultCategory(
        override val title: Int = InfoMenuDefaultCategoryTitle,
        val category: TodoType,
    ) : InfoMenu

    /**
     * Todo 나중에 구현합니다.
     */
    data class SettingAlarm(
        override val title: Int = InfoMenuSettingAlarmTitle,
        val isEnabled: Boolean,
    ) : InfoMenu

    companion object {
        val infoMenuList: ImmutableList<InfoMenu> = persistentListOf(
            ManageVacation(),
            DefaultCategory(category = TodoType.Personal()),
        )
    }
}
