package team.noweekend.core.common.ui.datepicker

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.toImmutableList
import kotlinx.datetime.LocalDate
import team.noweekend.core.common.ui.calendar.util.CalendarUtils.currentLocalDate
import team.noweekend.core.common.ui.calendar.util.CalendarUtils.isLeapYear
import team.noweekend.core.common.ui.calendar.util.CalendarUtils.monthLength
import team.noweekend.core.common.ui.datepicker.core.WheelPicker
import team.noweekend.core.common.ui.datepicker.model.WheelDate
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.core.resource.NWKStringResource.DayFormat
import team.noweekend.core.resource.NWKStringResource.MonthFormat
import team.noweekend.core.resource.NWKStringResource.YearFormat

@Composable
fun WheelDatePicker(
    onSelectedDate: (WheelDate) -> Unit,
    modifier: Modifier = Modifier,
    initialDate: LocalDate = currentLocalDate,
) {
    val monthList = remember {
        (1..12).toImmutableList()
    }
    val yearList = remember {
        (2000..2100).toImmutableList()
    }

    var selectedYear by remember { mutableIntStateOf(initialDate.year) }
    var selectedMonth by remember { mutableIntStateOf(initialDate.monthNumber) }
    var selectedDay by remember { mutableIntStateOf(initialDate.dayOfMonth) }

    val dayList = remember(selectedYear, selectedMonth) {
        val daysInMonth = selectedMonth.monthLength(isLeapYear = isLeapYear(selectedYear))
        (1..daysInMonth).toImmutableList()
    }

    val currentMonth = initialDate.monthNumber
    val currentYear = initialDate.year

    val yearIndex = yearList.indexOf(currentYear)
    val monthIndex = monthList.indexOf(currentMonth)
    val dayIndex = dayList.indexOf(selectedDay).coerceAtLeast(0)

    val itemHeight = 35.dp
    val containerWidth = 335.dp

    LaunchedEffect(selectedYear, selectedMonth, selectedDay) {
        onSelectedDate(
            WheelDate(
                year = selectedYear,
                month = selectedMonth,
                day = selectedDay,
            ),
        )
    }

    Box(
        modifier = modifier
            .width(containerWidth)
            .background(color = NWKTheme.color.Semantic.Background.normal),
        contentAlignment = Alignment.Center,
    ) {
        val selectedColor = NWKTheme.color.Semantic.Background.alternative01
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(itemHeight),
        ) {
            drawRoundRect(
                color = selectedColor,
                cornerRadius = CornerRadius(10f, 10f),
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 64.dp),
        ) {
            val yearItemList = yearList.map { year ->
                stringResource(id = YearFormat, year)
            }.toImmutableList()

            WheelPicker(
                modifier = Modifier.weight(1f),
                visibleItemCount = 7,
                initialIndex = yearIndex,
                itemList = yearItemList,
                itemHeight = itemHeight,
                onItemSelected = { index ->
                    selectedYear = yearList[index]
                },
            )

            val monthItemList = monthList.map { month ->
                stringResource(id = MonthFormat, month)
            }.toImmutableList()

            WheelPicker(
                modifier = Modifier.weight(1f),
                visibleItemCount = 7,
                initialIndex = monthIndex,
                itemList = monthItemList,
                itemHeight = itemHeight,
                onItemSelected = { index ->
                    selectedMonth = monthList[index]
                },
            )

            val updatedDayList = rememberUpdatedState(dayList)
            val dayItemList = dayList.map { day ->
                stringResource(id = DayFormat, day)
            }.toImmutableList()

            WheelPicker(
                modifier = Modifier.weight(1f),
                visibleItemCount = 7,
                initialIndex = dayIndex,
                itemList = dayItemList,
                itemHeight = itemHeight,
                onItemSelected = { index ->
                    selectedDay = updatedDayList.value[index]
                },
            )
        }
    }
}

@Preview
@Composable
private fun PreviewWheelDatePicker() {
    NWKTheme {
        WheelDatePicker(
            onSelectedDate = { date ->
                println(date)
            },
        )
    }
}
