package team.noweekend.feature.profile.manageVacation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ManageVacationRoute(
    modifier: Modifier = Modifier,
) {
    ManageVacationScreen(
        modifier = Modifier.fillMaxSize(),
        onClickBackButton = {},
        onClickSaveButton = {},
        hours = 4,
        days = 5,
    )
}
