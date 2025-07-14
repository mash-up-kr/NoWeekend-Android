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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.datetime.LocalTime
import team.noweekend.core.common.kotlin.extension.CalendarUtils.currentLocalDateTime
import team.noweekend.core.common.ui.datepicker.core.LocalTimeUtil.convertToLocalTime
import team.noweekend.core.common.ui.datepicker.core.WheelPicker
import team.noweekend.core.common.ui.datepicker.model.Meridiem
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.core.resource.NWKStringResource.HourFormat
import team.noweekend.core.resource.NWKStringResource.MinuteFormat

@Composable
fun WheelTimePicker(
    onSelectedTime: (LocalTime) -> Unit,
    modifier: Modifier = Modifier,
) {
    val meridiemList: ImmutableList<Meridiem> = Meridiem.entries.toImmutableList()
    val hourList: ImmutableList<Int> = (1..12).toImmutableList()
    val minuteList: ImmutableList<Int> = (0..59).toImmutableList()

    val hour24: Int = currentLocalDateTime.hour
    val hour12: Int = when {
        hour24 == 0 -> 12 // 자정
        hour24 > 12 -> hour24 - 12
        else -> hour24
    }

    val currentHourIndex = hourList.indexOf(hour12)
    val currentMinuteIndex = minuteList.indexOf(currentLocalDateTime.minute)
    val currentMeridiemIndex =
        meridiemList.indexOf(if (currentLocalDateTime.hour < 12) Meridiem.AM else Meridiem.PM)

    val itemHeight: Dp = 35.dp
    val containerWidth: Dp = 335.dp

    var selectedHour by remember { mutableIntStateOf(hourList[currentHourIndex]) }
    var selectedMinute by remember { mutableIntStateOf(minuteList[currentMinuteIndex]) }
    var selectedMeridiem by remember { mutableStateOf(meridiemList[currentMeridiemIndex]) }

    LaunchedEffect(selectedMeridiem, selectedHour, selectedMinute) {
        onSelectedTime(
            convertToLocalTime(
                meridiem = selectedMeridiem,
                hour = selectedHour,
                minute = selectedMinute,
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
                initialIndex = currentMeridiemIndex,
                itemList = meridiemList.map { meridiem: Meridiem ->
                    stringResource(id = meridiem.id)
                }.toImmutableList(),
                onItemSelected = { index ->
                    selectedMeridiem = meridiemList[index]
                },
            )

            val hourItemList = hourList.map { hour ->
                stringResource(id = HourFormat, hour)
            }.toImmutableList()

            WheelPicker(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                visibleItemCount = 7,
                initialIndex = currentHourIndex,
                itemList = hourItemList,
                onItemSelected = { index ->
                    selectedHour = hourList[index]
                },
            )

            val minuteItemList = minuteList.map { minute ->
                stringResource(id = MinuteFormat, minute)
            }.toImmutableList()

            WheelPicker(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                visibleItemCount = 7,
                initialIndex = currentMinuteIndex,
                itemList = minuteItemList,
                onItemSelected = { index ->
                    selectedMinute = minuteList[index]
                },
            )
        }
    }
}

@Preview
@Composable
private fun PreviewWheelTimePicker() {
    NWKTheme {
        WheelTimePicker(
            onSelectedTime = { time ->
                println(time)
            },
        )
    }
}
