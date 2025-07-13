package team.noweekend.feature.profile.schedule

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import team.noweekend.core.common.ui.schedule.FrequentScheduleComponent
import team.noweekend.core.common.ui.schedule.model.FrequentSchedule
import team.noweekend.core.design.system.core.component.button.defaults.BoxButtonType
import team.noweekend.core.design.system.core.component.button.fill.NWKFillButton
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.feature.profile.component.topbar.FrequentScheduleTopBar

@Composable
fun FrequentScheduleScreen(
    onClickBackButton: () -> Unit,
    onClickSaveButton: () -> Unit,
    onChipSelect: (FrequentSchedule) -> Unit,
    schedules: ImmutableList<FrequentSchedule>,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            FrequentScheduleTopBar(
                onClickBackButton = onClickBackButton,
                onClickSaveButton = onClickSaveButton,
            )
        },
        bottomBar = {
            NWKFillButton(
                onClick = {},
                text = "새로 추천 받기",
                modifier = Modifier.fillMaxWidth(),
                type = BoxButtonType.BLACK,
                isLoading = isLoading,
            )
        },
    ) { paddingValues ->
        FrequentScheduleComponent(
            modifier = Modifier.padding(paddingValues),
            schedules = schedules,
            onChipSelect = onChipSelect,
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun PreviewFrequentScheduleScreen() {
    NWKTheme {
        val schedules = persistentListOf(
            FrequentSchedule(
                title = "가족 모임", isSelected = false,
            ),
            FrequentSchedule(
                title = "넷플릭스 시청", isSelected = false,
            ),
            FrequentSchedule(
                title = "유튜브 시청", isSelected = false,
            ),
            FrequentSchedule(
                title = "치지직 시청", isSelected = false,
            ),
            FrequentSchedule(
                title = "데이트", isSelected = false,
            ),
        )
        FrequentScheduleScreen(
            modifier = Modifier.fillMaxSize(),
            onClickSaveButton = {},
            onClickBackButton = {},
            schedules = schedules,
            onChipSelect = {},
        )
    }
}
