package team.noweekend.feature.profile.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.core.resource.NWKStringResource.EtcMenuTitle
import team.noweekend.core.resource.NWKStringResource.InfoMenuTitle
import team.noweekend.feature.profile.component.VacationBoard
import team.noweekend.feature.profile.component.menu.MenuLayout
import team.noweekend.feature.profile.component.topbar.ProfileTopBar
import team.noweekend.feature.profile.model.EtcMenu.Companion.etcMenuList
import team.noweekend.feature.profile.model.InfoMenu.Companion.infoMenuList

@Composable
internal fun ProfileScreen(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = NWKTheme.color.Neutral.white),
    ) {
        ProfileTopBar(
            userName = "userName",
            onClickEditButton = {},
        )
        VacationBoard(
            lessVacationCount = 12.5f,
            usedVacationCount = 5.5f,
        )
        MenuLayout(
            title = stringResource(id = InfoMenuTitle),
            menuList = infoMenuList,
            onClickMenuItem = {}
        )
        Spacer(
            modifier = Modifier.height(16.dp),
        )

        MenuLayout(
            title = stringResource(id = EtcMenuTitle),
            menuList = etcMenuList,
            onClickMenuItem = {}
        )
    }


}


@Preview(showBackground = true)
@Composable
private fun PreviewProfileScreen(
    modifier: Modifier = Modifier,
) {
    NWKTheme {
        ProfileScreen(
            modifier = Modifier.fillMaxSize(),
        )
    }


}
