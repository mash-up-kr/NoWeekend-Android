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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import team.noweekend.core.common.ui.calendar.util.CalendarUtils.currentLocalDateTime
import team.noweekend.core.common.ui.datepicker.core.WheelPicker
import team.noweekend.core.design.system.foundation.theme.NWKTheme

@Composable
fun WheelTimePicker(
    modifier: Modifier = Modifier,
) {
    val amPm: ImmutableList<String> = persistentListOf("오전", "오후")
    val hour: ImmutableList<Int> = (1..12).toImmutableList()
    val minute: ImmutableList<Int> = (0..59).toImmutableList()

    val hour24 = currentLocalDateTime.hour // 0~23
    val hour12 = when {
        hour24 == 0 -> 12 // 자정
        hour24 > 12 -> hour24 - 12
        else -> hour24
    }

    val currentHourIndex = hour.indexOf(hour12)
    val currentMinuteIndex = minute.indexOf(currentLocalDateTime.minute)
    val currentAmPmIndex = amPm.indexOf(if (currentLocalDateTime.hour < 12) "오전" else "오후")

    val itemHeight = 35.dp
    val containerWidth = 335.dp

    val selectedHour = remember { mutableIntStateOf(hour[currentHourIndex]) }
    val selectedMinute = remember { mutableIntStateOf(minute[currentMinuteIndex]) }
    val selectedAmPm = remember { mutableStateOf(amPm[currentAmPmIndex]) }
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
                .padding(
                    horizontal = 64.dp,
                ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            WheelPicker(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                visibleItemCount = 3,
                initialIndex = currentAmPmIndex,
                itemList = amPm,
                onItemSelected = { index ->
                    selectedAmPm.value = amPm[index]
                },

            )

            WheelPicker(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                visibleItemCount = 7,
                initialIndex = currentHourIndex,
                itemList = hour.map { "${it}시" }.toImmutableList(),
                onItemSelected = { index ->
                    selectedHour.intValue = hour[index]
                },

            )

            WheelPicker(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                visibleItemCount = 7,
                initialIndex = currentMinuteIndex,
                itemList = minute.map { "${it}분" }.toImmutableList(),
                onItemSelected = { index ->
                    selectedMinute.intValue = minute[index]
                },
            )
        }
    }
}

@Preview
@Composable
private fun PreviewWheelTimePicker() {
    NWKTheme {
        WheelTimePicker()
    }
}
