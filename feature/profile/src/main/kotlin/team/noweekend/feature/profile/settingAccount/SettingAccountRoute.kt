package team.noweekend.feature.profile.settingAccount

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SettingAccountRoute(
    modifier: Modifier = Modifier
) {

    SettingAccountScreen(
        modifier = modifier.fillMaxSize(),
        onclickBackButton = {},
        onClickSaveButton = {}
    )
}
