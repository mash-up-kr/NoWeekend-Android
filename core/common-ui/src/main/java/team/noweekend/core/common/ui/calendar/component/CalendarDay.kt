package team.noweekend.core.common.ui.calendar.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.noweekend.core.common.ui.calendar.CalendarDataProvider.ImageType
import team.noweekend.core.common.ui.calendar.model.DateOfWeek
import team.noweekend.core.common.ui.calendar.state.CalendarPagerState.CalendarMode
import team.noweekend.core.common.ui.calendar.util.CalendarUtils.now
import team.noweekend.core.design.system.foundation.theme.NWKTheme

@Composable
internal fun CalendarDay(
    dateOfWeek: DateOfWeek,
    calendarMode: CalendarMode,
    modifier: Modifier = Modifier,
    isSelectedDay: Boolean = false,
    isCurrentMonth: Boolean = false,
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    onClickDateOfWeek: (DateOfWeek) -> Unit = {},
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
                enabled = isCurrentMonth,
            ) {
                onClickDateOfWeek(dateOfWeek)
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
                text = dateOfWeek.localDate.dayOfMonth.toString(),
                color = if (isSelectedDay) {
                    NWKTheme.color.Toast.toast700
                } else {
                    NWKTheme.color.Neutral.neutralGray900
                },
                textAlign = TextAlign.Center,
            )
        }
        Row(
            modifier = Modifier.size(41.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = dateOfWeek.imageType.id),
                contentDescription = null,
            )
        }
    }
}

@Preview
@Composable
private fun PreviewCalendarDay() {
    NWKTheme {
        val targetDate = now()
        val date = DateOfWeek(
            localDate = targetDate,
            imageType = ImageType.NONE,
        )
        CalendarDay(
            dateOfWeek = date,
            isSelectedDay = true,
            isCurrentMonth = true,
            calendarMode = CalendarMode.WEEK,
        )
    }
}
