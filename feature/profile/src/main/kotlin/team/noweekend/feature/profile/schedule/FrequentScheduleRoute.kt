package team.noweekend.feature.profile.schedule

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.collections.immutable.persistentListOf

@Composable
fun FrequentScheduleRoute(modifier: Modifier = Modifier) {

    FrequentScheduleScreen(
        modifier = modifier,
        onChipSelect = {},
        onClickBackButton = {},
        onClickSaveButton = {},
        schedules = persistentListOf(),
        isLoading = false,
    )
}

