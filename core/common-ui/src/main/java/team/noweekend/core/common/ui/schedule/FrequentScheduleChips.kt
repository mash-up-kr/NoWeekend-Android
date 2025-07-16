package team.noweekend.core.common.ui.schedule

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.collections.immutable.ImmutableList
import team.noweekend.core.common.ui.chip.Chip
import team.noweekend.core.common.ui.schedule.model.FrequentSchedule
import team.noweekend.core.design.system.foundation.theme.NWKTheme

@Composable
fun FrequentScheduleChips(
    schedules: ImmutableList<FrequentSchedule>,
    onChipSelect: (FrequentSchedule) -> Unit,
    modifier: Modifier = Modifier,
) {
    FlowRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(NWKTheme.spacing.space100, Alignment.CenterHorizontally),
        verticalArrangement = Arrangement.spacedBy(NWKTheme.spacing.space100),
    ) {
        schedules.forEach {
            key(it.title) {
                Chip(
                    text = it.title,
                    isSelected = it.isSelected,
                    onChipSelect = { onChipSelect(it) },
                )
            }
        }
    }
}
