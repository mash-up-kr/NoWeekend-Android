package team.noweekend.feature.calendar.component.choose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.datetime.LocalDate
import team.noweekend.core.common.kotlin.extension.now
import team.noweekend.core.common.ui.calendar.component.CalendarTypeToggle
import team.noweekend.core.common.ui.calendar.state.CalendarPagerState.CalendarMode
import team.noweekend.core.design.system.foundation.theme.NWKTheme

@Composable
internal fun YearMonthCalendarTypeChooser(
    date: State<LocalDate>,
    calendarMode: CalendarMode,
    onClickYearMonthButton: () -> Unit,
    onToggleStateChanged: (Boolean) -> Unit,
    onClickToggle: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ChooseYearMonthButton(
            date = date,
            onClick = onClickYearMonthButton,
        )

        CalendarTypeToggle(
            currentCalendarMode = calendarMode,
            onToggleStateChanged = onToggleStateChanged,
            onClickToggle = onClickToggle,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewYearMonthCalendarChooser() {
    NWKTheme {
        var calendarMode by remember { mutableStateOf(CalendarMode.MONTH) }
        val date = remember { mutableStateOf(LocalDate.now()) }
        YearMonthCalendarTypeChooser(
            date = date,
            calendarMode = calendarMode,
            onClickYearMonthButton = {},
            onToggleStateChanged = { isMonth ->
                if (isMonth) {
                    calendarMode = CalendarMode.MONTH
                } else {
                    calendarMode = CalendarMode.WEEK
                }
            },
            onClickToggle = {
                calendarMode = if (calendarMode == CalendarMode.WEEK) {
                    CalendarMode.MONTH
                } else {
                    CalendarMode.WEEK
                }
            },
        )
    }
}
