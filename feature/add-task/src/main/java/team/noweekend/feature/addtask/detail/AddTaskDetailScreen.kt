package team.noweekend.feature.addtask.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import team.noweekend.core.common.kotlin.extension.KOREAN_MERIDIEM_HOUR_MINUTE_PATTERN
import team.noweekend.core.common.kotlin.extension.YEAR_MONTH_DATE_WITH_DAY_OF_WEEK_PATTERN
import team.noweekend.core.common.kotlin.extension.now
import team.noweekend.core.common.kotlin.extension.toFormattedString
import team.noweekend.core.common.ui.datepicker.WheelDatePicker
import team.noweekend.core.common.ui.datepicker.WheelTimePicker
import team.noweekend.core.common.ui.datepicker.model.DatePickerType
import team.noweekend.core.design.system.core.component.header.NWKHeader
import team.noweekend.core.design.system.core.component.input.NWKInputField
import team.noweekend.core.design.system.core.component.input.status.TextInputType
import team.noweekend.core.design.system.core.component.scaffold.NWKScaffold
import team.noweekend.core.design.system.core.component.toggle.Toggle
import team.noweekend.core.design.system.core.component.toggle.ToggleState
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.core.resource.NWKStringResource

@Composable
fun AddTaskDetailScreen(
    onBackClick: () -> Unit,
    onClickAction: (String) -> Unit,
    onClickInfoSave: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var toggleState by remember { mutableStateOf(ToggleState.OFF) }
    var starDateIsClick by remember { mutableStateOf(false) }
    var startTimeIsClick by remember { mutableStateOf(false) }
    var endDateIsClick by remember { mutableStateOf(false) }
    var endTimeIsClick by remember { mutableStateOf(false) }
    var startDate by remember { mutableStateOf(LocalDate.now()) }
    var startTIme by remember { mutableStateOf(LocalTime(hour = 0, minute = 0)) }
    var endDate by remember { mutableStateOf(LocalDate.now()) }
    var endTime by remember { mutableStateOf(LocalTime(hour = 0, minute = 0)) }
    val passionTemperatureState = rememberTextFieldState(initialText = "5")

    NWKScaffold(
        modifier = modifier,
        topBar = {
            NWKHeader(
                modifier = Modifier.fillMaxWidth(),
                onBackClick = onBackClick,
                text = stringResource(NWKStringResource.AddTaskDetailLabel),
                content = {
                    Text(
                        modifier = Modifier.clickable { onClickInfoSave() },
                        text = stringResource(NWKStringResource.InputTextSaveLabel),
                        style = NWKTheme.typography.heading6,
                        color = NWKTheme.color.Toast.toast500,
                    )
                },
            )
        },
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(NWKTheme.spacing.space300),
        ) {
            TodoDateContainer(
                modifier = Modifier.fillMaxWidth(),
                toggleState = toggleState,
                onToggleStateChanged = { toggleState = it },
            )
            TodoTimeContainer(
                label = stringResource(NWKStringResource.AddTaskStartLabel),
                modifier = Modifier.fillMaxWidth(),
                toggleState = toggleState,
                onClickDate = { starDateIsClick = it },
                date = startDate,
                time = startTIme,
                onClickTime = { startTimeIsClick = it },
            )
            if (starDateIsClick) {
                WheelDatePicker(
                    wheelDatePickerType = DatePickerType.YearMonth,
                    onSelectedDate = { startDate = it },
                )
            } else if (startTimeIsClick) {
                WheelTimePicker(
                    onSelectedTime = {
                        startTIme = it
                    },
                )
            }
            TodoTimeContainer(
                label = stringResource(NWKStringResource.AddTaskEndLabel),
                modifier = Modifier.fillMaxWidth(),
                toggleState = toggleState,
                onClickDate = { endDateIsClick = it },
                date = endDate,
                time = endTime,
                onClickTime = { endTimeIsClick = it },
            )
            if (endDateIsClick) {
                WheelDatePicker(
                    wheelDatePickerType = DatePickerType.YearMonth,
                    onSelectedDate = { endDate = it },
                )
            } else if (endTimeIsClick) {
                WheelTimePicker(
                    onSelectedTime = { time ->
                        endTime = time
                    },
                )
            }
            PassionTemperatureContainer(
                modifier = Modifier.fillMaxWidth(),
                textState = passionTemperatureState,
                onClickAction = onClickAction,
            )
        }
    }
}

@Composable
private fun PassionTemperatureContainer(
    textState: TextFieldState,
    modifier: Modifier = Modifier,
    onClickAction: (String) -> Unit,
) {
    val lineColor = NWKTheme.color.Semantic.Border.border02

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column {
            Text(
                text = stringResource(NWKStringResource.PassionTemperature),
                style = NWKTheme.typography.subTitle1,
                color = NWKTheme.color.Semantic.Text.neutral,
            )
            Text(
                modifier = Modifier.padding(top = 2.dp),
                text = stringResource(NWKStringResource.PassionTemperatureDescription),
                style = NWKTheme.typography.body2,
                color = NWKTheme.color.Semantic.Text.body,
            )
        }
        Row(
            modifier = Modifier
                .drawBehind {
                    drawLine(
                        color = lineColor,
                        start = Offset(0f, size.height - 1f),
                        end = Offset(size.width, size.height - 1f),
                        strokeWidth = 1.dp.toPx(),
                    )
                }
                .padding(bottom = NWKTheme.spacing.space100),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            NumberInputField(
                modifier = Modifier.width(70.dp),
                onClickAction = onClickAction,
                textState = textState,
            )
            Text(
                modifier = Modifier.padding(start = NWKTheme.spacing.space150),
                text = stringResource(NWKStringResource.PassionTemperatureLabel),
                style = NWKTheme.typography.body1,
                color = NWKTheme.color.Semantic.Text.neutral,
            )
        }
    }
}

@Composable
private fun NumberInputField(
    onClickAction: (String) -> Unit,
    textState: TextFieldState,
    modifier: Modifier = Modifier,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    NWKInputField(
        modifier = modifier,
        isUnderLine = false,
        textFieldState = textState,
        textInputType = TextInputType.NUMBER,
        underLinePadding = 0.dp,
        onKeyboardAction = {
            onClickAction(textState.text.toString())
            focusManager.clearFocus()
            keyboardController?.hide()
        },
        inputTransformation = {
            val numericText = asCharSequence().filter { it.isDigit() }
            // 숫자 범위 (0~100)로 제한
            val limitedValue = numericText.toString().toIntOrNull()?.coerceIn(0, 100)
            // 최종 값 설정 (null일 경우 빈칸)
            val result = limitedValue?.toString() ?: ""
            // 입력 필드에 최종 결과를 적용
            replace(0, length, result)
        },
    )
}

@Composable
fun TodoTimeContainer(
    label: String,
    toggleState: ToggleState,
    onClickDate: (Boolean) -> Unit,
    onClickTime: (Boolean) -> Unit,
    date: LocalDate,
    time: LocalTime,
    modifier: Modifier = Modifier,
) {
    var isDateClick by remember { mutableStateOf(false) }
    var isTimeClick by remember { mutableStateOf(false) }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = label,
            style = NWKTheme.typography.subTitle1,
            color = NWKTheme.color.Semantic.Text.neutral,
        )
        Row(horizontalArrangement = Arrangement.spacedBy(22.dp)) {
            DateLineText(
                modifier = Modifier.clickable {
                    isDateClick = !isDateClick
                    isTimeClick = false
                    onClickTime(false)
                    onClickDate(isDateClick)
                },
                text = date.toFormattedString(
                    pattern = LocalDate.YEAR_MONTH_DATE_WITH_DAY_OF_WEEK_PATTERN,
                ),
            )
            if (toggleState == ToggleState.OFF) {
                DateLineText(
                    modifier = Modifier.clickable {
                        isTimeClick = !isTimeClick
                        isDateClick = false
                        onClickDate(false)
                        onClickTime(isTimeClick)
                    },
                    text = time.toFormattedString(
                        pattern = LocalTime.KOREAN_MERIDIEM_HOUR_MINUTE_PATTERN,
                    ),
                )
            }
        }
    }
}

@Composable
private fun TodoDateContainer(
    toggleState: ToggleState,
    modifier: Modifier = Modifier,
    onToggleStateChanged: (ToggleState) -> Unit,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = stringResource(NWKStringResource.AddTaskAllDay),
            style = NWKTheme.typography.subTitle1,
            color = NWKTheme.color.Semantic.Text.neutral,
        )
        Toggle(
            toggleState = toggleState,
            onToggleStateChanged = { isOn ->
                if (isOn) {
                    ToggleState.ON
                } else {
                    ToggleState.OFF
                }
            },
            onClickToggle = {
                onToggleStateChanged(
                    if (toggleState == ToggleState.OFF) {
                        ToggleState.ON
                    } else {
                        ToggleState.OFF
                    },
                )
            },
        )
    }
}

@Composable
private fun DateLineText(text: String, modifier: Modifier = Modifier) {
    val lineColor = NWKTheme.color.Semantic.Border.border02
    Text(
        modifier = modifier
            .drawBehind {
                drawLine(
                    color = lineColor,
                    start = Offset(0f, size.height - 1f),
                    end = Offset(size.width, size.height - 1f),
                    strokeWidth = 1.dp.toPx(),
                )
            }
            .padding(bottom = NWKTheme.spacing.space100),
        text = text,
        style = NWKTheme.typography.body1,
        color = NWKTheme.color.Semantic.Text.neutral,
    )
}

@Preview
@Composable
private fun AddTaskDetailScreenPreview() {
    AddTaskDetailScreen(
        onBackClick = {},
        onClickInfoSave = {},
        modifier = Modifier.fillMaxSize(),
        onClickAction = {},
    )
}
