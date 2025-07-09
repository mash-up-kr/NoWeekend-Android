package team.noweekend.feature.profile.editInfo.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.feature.profile.component.menu.MenuLayout
import team.noweekend.feature.profile.component.topbar.EditInfoTopBar
import team.noweekend.feature.profile.editInfo.model.EditInfoMenu
import team.noweekend.feature.profile.editInfo.model.EditInfoMenu.Companion.editInfoMenuList

@Composable
internal fun ProfileEditInfoScreen(
    accountName: String,
    onClickBackButton: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val editInfoMenus: ImmutableList<EditInfoMenu> = remember(accountName) {
        editInfoMenuList.map { menu: EditInfoMenu ->
            if (menu is EditInfoMenu.Account) {
                menu.copy(
                    accountName = accountName,
                )
            } else {
                menu
            }
        }.toImmutableList()
    }

    Column(modifier = modifier) {
        EditInfoTopBar(
            onClickBackButton = onClickBackButton,
        )
        MenuLayout(
            menuList = editInfoMenus,
            onClickMenuItem = {},
        )
    }
}


@Preview
@Composable
private fun PreviewProfileEditInfoScreen() {
    NWKTheme {
        ProfileEditInfoScreen(
            modifier = Modifier.fillMaxSize(),
            accountName = "김매숑",
            onClickBackButton = {},
        )
    }
}
