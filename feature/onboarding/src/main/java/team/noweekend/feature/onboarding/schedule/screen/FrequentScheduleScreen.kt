package team.noweekend.feature.onboarding.schedule.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import team.noweekend.core.common.ui.schedule.FrequentScheduleComponent
import team.noweekend.core.common.ui.schedule.model.FrequentSchedule
import team.noweekend.core.design.system.core.component.button.defaults.BoxButtonType
import team.noweekend.core.design.system.core.component.button.fill.NWKFillButton
import team.noweekend.core.design.system.core.component.header.NWKHeader
import team.noweekend.core.design.system.core.component.scaffold.NWKScaffold
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.core.resource.NWKStringResource
import team.noweekend.feature.onboarding.mvi.OnboardUiState

@Composable
internal fun FrequentScheduleScreen(
    onBackClick: () -> Unit,
    onConfirmClick: (ImmutableList<FrequentSchedule>) -> Unit,
    onSelectedChip: (FrequentSchedule) -> Unit,
    uiState: OnboardUiState,
    modifier: Modifier = Modifier,
) {
    val scheduleList = remember { mutableListOf<FrequentSchedule>() }

    NWKScaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            NWKHeader(
                onBackClick = onBackClick,
                text = "3/3",
            )
        },
        content = {
            FrequentScheduleScreenContent(
                frequentSchedules = uiState.scheduleTags,
                onScheduleSelect = {
                    onSelectedChip(it)
                    scheduleList.add(it)
                },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
            )
        },
        bottomBar = {
            NWKFillButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 8.dp),
                onClick = { onConfirmClick(scheduleList.toImmutableList()) },
                text = stringResource(NWKStringResource.Start),
                type = BoxButtonType.BLACK,
                enabled = scheduleList.size >= 3,
            )
        },
    )
}

@Composable
private fun FrequentScheduleScreenContent(
    frequentSchedules: ImmutableList<FrequentSchedule>,
    onScheduleSelect: (FrequentSchedule) -> Unit,
    modifier: Modifier = Modifier,
) {
    FrequentScheduleComponent(
        schedules = frequentSchedules,
        onChipSelect = onScheduleSelect,
        modifier = modifier,
    )
}

@Preview
@Composable
private fun FrequentScheduleScreenPreview() {
    NWKTheme {
        FrequentScheduleScreen(
            onBackClick = {},
            onConfirmClick = {},
            uiState = OnboardUiState.INITIAL_STATE,
            onSelectedChip = {},
        )
    }
}
