package team.noweekend.core.common.ui.calendar.component

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import team.noweekend.core.common.ui.calendar.model.CalendarDay
import team.noweekend.core.design.system.foundation.theme.NWKTheme

@Composable
internal fun DayOfWeekBar(
    modifier: Modifier = Modifier,
    isMondayStarted: Boolean = false,
) {
    Row(
        modifier = modifier,
    ) {
        CalendarDay.getDays(isMondayStarted = isMondayStarted).forEach { day ->
            key(day.id) {
                DayOfWeekBarContent(
                    modifier = Modifier.weight(1f),
                    calendarDay = day,
                )
            }
        }
    }
}

@Composable
private fun DayOfWeekBarContent(
    calendarDay: CalendarDay,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier,
        text = stringResource(id = calendarDay.id),
        color = calendarDay.color,
        textAlign = TextAlign.Center,
        style = NWKTheme.typography.body3,
    )
}

@Preview
@Composable
private fun PreviewDayOfWeekBar() {
    NWKTheme {
        DayOfWeekBar()
    }
}
