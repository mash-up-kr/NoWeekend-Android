package team.noweekend.core.common.ui.schedule

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import team.noweekend.core.common.ui.schedule.model.FrequentSchedule
import team.noweekend.core.common.ui.schedule.preview.PreviewFrequentSchedulesProvider
import team.noweekend.core.design.system.foundation.theme.NWKTheme

@Composable
fun FrequentScheduleComponent(
    schedules: ImmutableList<FrequentSchedule>,
    onChipSelect: (FrequentSchedule) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        Spacer(modifier = Modifier.size(48.dp))
        FrequentScheduleHeader()
        Spacer(modifier = Modifier.size(NWKTheme.spacing.space500))
        FrequentScheduleChips(
            schedules = schedules,
            onChipSelect = onChipSelect,
        )
    }
}

@Preview
@Composable
private fun FrequentScheduleComponentPreview(
    @PreviewParameter(PreviewFrequentSchedulesProvider::class) schedules: ImmutableList<FrequentSchedule>,
) {
    NWKTheme {
        FrequentScheduleComponent(
            schedules = schedules,
            onChipSelect = {},
            modifier = Modifier.background(NWKTheme.color.Neutral.white),
        )
    }
}
