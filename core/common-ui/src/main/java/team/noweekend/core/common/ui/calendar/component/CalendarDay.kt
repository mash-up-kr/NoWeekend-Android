package team.noweekend.core.common.ui.calendar.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.datetime.LocalDate
import team.noweekend.core.common.kotlin.extension.now
import team.noweekend.core.common.ui.calendar.model.CalendarImageType
import team.noweekend.core.common.ui.calendar.model.CalendarDateOfWeek
import team.noweekend.core.common.ui.calendar.model.CalendarMode
import team.noweekend.core.design.system.foundation.theme.NWKTheme

@Composable
internal fun CalendarDay(
    calendarDateOfWeek: CalendarDateOfWeek,
    calendarMode: CalendarMode,
    calendarDayClickable: Boolean,
    modifier: Modifier = Modifier,
    isSelectedDay: Boolean = false,
    isCurrentMonth: Boolean = false,
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    onClickDateOfWeek: (CalendarDateOfWeek) -> Unit = {},
) {
    val condition = if (calendarMode == CalendarMode.MONTH) {
        if (isCurrentMonth) 1f else 0f
    } else {
        1f
    }
    Column(
        modifier = modifier
            .graphicsLayer {
                alpha = condition
            }
            .clickable(
                enabled = (isCurrentMonth || calendarMode == CalendarMode.WEEK) && calendarDayClickable,
            ) {
                onClickDateOfWeek(calendarDateOfWeek)
            },
        horizontalAlignment = horizontalAlignment,
    ) {
        Row(
            modifier = Modifier
                .size(41.dp)
                .padding(4.5.dp)
                .background(
                    color = if (isSelectedDay) NWKTheme.color.Toast.toast100 else Color.Transparent,
                    shape = CircleShape,
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                text = calendarDateOfWeek.localDate.dayOfMonth.toString(),
                color = if (isSelectedDay) {
                    NWKTheme.color.Toast.toast700
                } else {
                    NWKTheme.color.Neutral.neutralGray900
                },
                textAlign = TextAlign.Center,
                style = NWKTheme.typography.subTitle1,
            )
        }
        Image(
            modifier = Modifier.size(41.dp),
            painter = painterResource(id = calendarDateOfWeek.calendarImageType.id),
            contentDescription = null,
        )
    }
}

@Preview
@Composable
private fun PreviewCalendarDay() {
    NWKTheme {
        val targetDate = LocalDate.now()
        val date = CalendarDateOfWeek(
            localDate = targetDate,
            calendarImageType = CalendarImageType.NONE,
            isCurrentDate = true,
        )
        CalendarDay(
            calendarDateOfWeek = date,
            isSelectedDay = true,
            isCurrentMonth = true,
            calendarMode = CalendarMode.WEEK,
            calendarDayClickable = true,
        )
    }
}
