package team.noweekend.feature.onboarding.vacation.screen

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.noweekend.core.common.ui.vacation.RemainedVacationComponent
import team.noweekend.core.design.system.core.component.button.defaults.BoxButtonType
import team.noweekend.core.design.system.core.component.button.fill.NWKFillButton
import team.noweekend.core.design.system.core.component.header.NWKHeader
import team.noweekend.core.design.system.core.component.scaffold.NWKScaffold
import team.noweekend.core.design.system.core.component.toggle.ToggleState
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.core.resource.NWKStringResource
import team.noweekend.feature.onboarding.mvi.OnboardUiState

@Composable
internal fun RemainedVacationInputScreen(
    uiState: OnboardUiState,
    onBackClick: () -> Unit,
    onHalfVacationClick: (Boolean) -> Unit,
    onConfirmClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val vacationDayTextFieldState = rememberTextFieldState(uiState.vacationDay.toString())

    NWKScaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            NWKHeader(
                onBackClick = onBackClick,
                text = "2/3",
            )
        },
        content = {
            RemainedVacationInputScreenContent(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                days = vacationDayTextFieldState.text.toString().toIntOrNull() ?: 0,
                hours = uiState.vacationHour,
                vacationStateDay = vacationDayTextFieldState,
                onHalfVacationClick = onHalfVacationClick,
            )
        },
        bottomBar = {
            NWKFillButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 8.dp),
                onClick = { onConfirmClick(vacationDayTextFieldState.text.toString()) },
                text = stringResource(NWKStringResource.Next),
                type = BoxButtonType.BLACK,
                enabled = vacationDayTextFieldState.text.isNotEmpty(),
            )
        },
    )
}

@Composable
private fun RemainedVacationInputScreenContent(
    days: Int,
    hours: Int,
    onHalfVacationClick: (Boolean) -> Unit,
    vacationStateDay: TextFieldState,
    modifier: Modifier = Modifier,
) {
    val focusRequester: FocusRequester = remember { FocusRequester() }
    val focusManager: FocusManager = LocalFocusManager.current
    var toggleState by remember { mutableStateOf(ToggleState.OFF) }

    RemainedVacationComponent(
        days = days,
        hours = hours,
        vacationState = vacationStateDay,
        focusRequester = focusRequester,
        onKeyboardAction = {
            focusManager.clearFocus()
        },
        isToggleOn = toggleState == ToggleState.ON,
        onHalfVacationClick = {
            toggleState = if (toggleState == ToggleState.ON) {
                Log.d("RemainedVacationInputScreen", "Half vacation off")
                onHalfVacationClick(false)
                ToggleState.OFF
            } else {
                Log.d("RemainedVacationInputScreen", "Half vacation on")
                onHalfVacationClick(true)
                ToggleState.ON
            }
        },
        modifier = modifier,
    )
}

@Preview
@Composable
private fun RemainedVacationInputScreenPreview() {
    NWKTheme {
        RemainedVacationInputScreen(
            onBackClick = {},
            onConfirmClick = {},
            modifier = Modifier.fillMaxSize(),
            uiState = OnboardUiState.INITIAL_STATE,
            onHalfVacationClick = {},
        )
    }
}
