package team.noweekend.feature.profile.editInfo.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import team.noweekend.core.design.system.core.component.scaffold.NWKScaffold
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.core.resource.NWKStringResource.EditInfoDeleteAccountTitle
import team.noweekend.feature.profile.component.menu.MenuLayout
import team.noweekend.feature.profile.component.topbar.EditInfoTopBar
import team.noweekend.feature.profile.editInfo.model.EditInfoMenu
import team.noweekend.feature.profile.editInfo.model.EditInfoMenu.Companion.editInfoMenuList

@Composable
internal fun ProfileEditInfoScreen(
    accountName: String,
    onClickBackButton: () -> Unit,
    onClickRemoveAccount: () -> Unit,
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

    NWKScaffold(
        modifier = modifier,
        topBar = {
            EditInfoTopBar(
                onClickBackButton = onClickBackButton,
            )
        },
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .clickable(onClick = onClickRemoveAccount),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = stringResource(id = EditInfoDeleteAccountTitle),
                    style = NWKTheme.typography.body1,
                    color = NWKTheme.color.Semantic.Text.body,
                )

            }
        },
    ) { paddingValues ->
        MenuLayout(
            modifier = Modifier.fillMaxSize().padding(paddingValues),
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
            onClickRemoveAccount = {},
        )
    }
}
