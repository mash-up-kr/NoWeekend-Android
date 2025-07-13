package team.noweekend.feature.addtask.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.datetime.LocalDate
import team.noweekend.core.common.kotlin.extension.now
import team.noweekend.core.common.ui.datepicker.WheelDatePicker
import team.noweekend.core.common.ui.datepicker.model.DatePickerType
import team.noweekend.core.design.system.core.component.header.NWKHeader
import team.noweekend.core.design.system.core.component.scaffold.NWKScaffold
import team.noweekend.core.design.system.core.component.toggle.Toggle
import team.noweekend.core.design.system.core.component.toggle.ToggleState
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.core.resource.NWKStringResource

@Composable
fun AddTaskDetailScreen(
    onBackClick: () -> Unit,
    onClickInfoSave: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var toggleState by remember { mutableStateOf(ToggleState.OFF) }
    var isClick by remember { mutableStateOf(false) }
    var date by remember { mutableStateOf(LocalDate.now()) }

    NWKScaffold(
        modifier = modifier, topBar = {
            NWKHeader(
                modifier = Modifier.fillMaxWidth(),
                onBackClick = onBackClick,
                text = "세부사항",
                content = {
                    Text(
                        modifier = Modifier.clickable { onClickInfoSave() },
                        text = stringResource(NWKStringResource.InputTextSaveLabel),
                        style = NWKTheme.typography.heading6,
                        color = NWKTheme.color.Toast.toast500,
                    )
                },
            )
        }) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .scrollable(
                    state = rememberScrollState(),
                    orientation = Orientation.Vertical,
                ), verticalArrangement = Arrangement.spacedBy(NWKTheme.spacing.space300)
        ) {
            TodoDateContainer(
                modifier = Modifier.fillMaxWidth(),
                toggleState = toggleState,
                onToggleStateChanged = { toggleState = it })
            TodoStartTimeContainer(
                modifier = Modifier.fillMaxWidth(), toggleState = toggleState,
                onClickDate = { isClick = it },
                date = date
            )
            if (isClick) {
                WheelDatePicker(
                    wheelDatePickerType = DatePickerType.YearMonth,
                    onSelectedDate = { date = it },
                )
            }
        }
    }
}

@Composable
fun TodoStartTimeContainer(
    toggleState: ToggleState,
    onClickDate: (Boolean) -> Unit,
    date: LocalDate,
    modifier: Modifier = Modifier,
) {
    var isClick by remember { mutableStateOf(false) }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "시작")
        DateLineText(
            modifier = Modifier.clickable {
                onClickDate(!isClick)
            },
            date = date.toString()
        )
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
            text = "하루 종일"
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
                    }
                )
            },
        )
    }
}

@Composable
private fun DateLineText(date: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = date,
        )
        HorizontalDivider(
            modifier = Modifier
                .padding(top = NWKTheme.spacing.space100),
            thickness = 1.dp,
            color = NWKTheme.color.Semantic.Border.border02
        )
    }
}


@Preview
@Composable
private fun DateLineTextPreview() {
    AddTaskDetailScreen(
        onBackClick = {},
        onClickInfoSave = {},
        modifier = Modifier.fillMaxSize()
    )
}
