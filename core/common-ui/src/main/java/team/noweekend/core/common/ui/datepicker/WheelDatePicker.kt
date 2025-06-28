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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.toImmutableList
import kotlinx.datetime.LocalDate
import team.noweekend.core.common.ui.calendar.util.CalendarUtils.getDaysInMonth
import team.noweekend.core.common.ui.calendar.util.CalendarUtils.now
import team.noweekend.core.common.ui.datepicker.core.WheelPicker
import team.noweekend.core.design.system.foundation.theme.NWKTheme

@Composable
fun WheelDatePicker(
    modifier: Modifier = Modifier,
    initialDate: LocalDate = now(),
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
        val daysInMonth = getDaysInMonth(selectedYear, selectedMonth)
        (1..daysInMonth).toImmutableList()
    }

    val cacheDayList = remember(dayList.size) {
        dayList.map { "${it}일" }.toImmutableList()
    }

    val currentMonth = initialDate.monthNumber
    val currentYear = initialDate.year

    val yearIndex = yearList.indexOf(currentYear)
    val monthIndex = monthList.indexOf(currentMonth)
    val dayIndex = dayList.indexOf(selectedDay).coerceAtLeast(0)


    val itemHeight = 35.dp
    val containerWidth = 335.dp


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
            WheelPicker(
                modifier = Modifier.weight(1f),
                visibleItemCount = 7,
                initialIndex = yearIndex,
                itemList = yearList.map { "${it}년" }.toImmutableList(),
                itemHeight = itemHeight,
                onItemSelected = {
                    selectedYear = yearList[it]
                },
            )

            WheelPicker(
                modifier = Modifier.weight(1f),
                visibleItemCount = 7,
                initialIndex = monthIndex,
                itemList = monthList.map { "${it}월" }.toImmutableList(),
                itemHeight = itemHeight,
                onItemSelected = {
                    selectedMonth = monthList[it]
                },
            )

            val updatedDayList = rememberUpdatedState(dayList)

            WheelPicker(
                modifier = Modifier.weight(1f),
                visibleItemCount = 7,
                initialIndex = dayIndex,
                itemList = cacheDayList,
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
        WheelDatePicker()
    }
}
