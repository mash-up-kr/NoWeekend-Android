package team.noweekend.feature.profile.editInfo.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun ProfileEditInfoRoute(
    modifier: Modifier = Modifier,
) {
    ProfileEditInfoScreen(
        modifier = modifier,
        accountName = "",
        onClickBackButton = {},
        onClickRemoveAccount = {}
    )

}
