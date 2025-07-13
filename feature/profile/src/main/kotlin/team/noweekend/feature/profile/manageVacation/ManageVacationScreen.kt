package team.noweekend.feature.profile.manageVacation

import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import team.noweekend.core.common.ui.vacation.RemainedVacationComponent
import team.noweekend.core.design.system.core.component.input.status.InputFieldStatus
import team.noweekend.core.design.system.core.component.scaffold.NWKScaffold
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.feature.profile.component.topbar.ManageVacationTopBar

@Composable
fun ManageVacationScreen(
    onClickBackButton: () -> Unit,
    onClickSaveButton: () -> Unit,
    hours: Int,
    days: Int,
    modifier: Modifier = Modifier,
) {
    val focusRequester = remember { FocusRequester() }
    var toggleState by remember { mutableStateOf(days != 0) }
    val hoursWithToggleState = if (toggleState) hours else 0
    val vacationState: TextFieldState = rememberTextFieldState(initialText = days.toString())
    val inputFieldStatus by remember {
        derivedStateOf {
            val vacationStateText = vacationState.text.toString()
            if (checkIsAllDigit(vacationStateText)) {
                InputFieldStatus.DEFAULT
            } else {
                InputFieldStatus.ERROR
            }
        }
    }
    val onlyDigitVacationState by remember{
        derivedStateOf {
            vacationState.text.filter { it.isDigit() }.toString().toIntOrNull() ?: 0
        }
    }


    val focusManager = LocalFocusManager.current

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }


    NWKScaffold(
        modifier = modifier,
        topBar = {
            ManageVacationTopBar(
                onClickBackButton = onClickBackButton,
                onClickSaveButton = onClickSaveButton,
            )

        },
    ) { paddingValues ->
        RemainedVacationComponent(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .focusable(),
            hours = hoursWithToggleState,
            days = onlyDigitVacationState,
            vacationState = vacationState,
            focusRequester = focusRequester,
            onKeyboardAction = {
                if(inputFieldStatus != InputFieldStatus.ERROR){
                    focusManager.clearFocus()
                }

            },
            onHalfVacationClick = {
                toggleState = toggleState.not()
            },
            inputFieldStatus = inputFieldStatus,
            isToggleOn = toggleState,
        )
    }
}

fun checkIsAllDigit(text: String): Boolean {
    return text.all { it.isDigit() }
}

@Preview
@Composable
private fun PreviewManageVacationScreen() {
    NWKTheme {
        ManageVacationScreen(
            modifier = Modifier.fillMaxSize(),
            onClickBackButton = {},
            onClickSaveButton = {},
            hours = 4,
            days = 2,
        )
    }
}
