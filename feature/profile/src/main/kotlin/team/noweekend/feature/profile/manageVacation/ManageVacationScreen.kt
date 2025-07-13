package team.noweekend.feature.profile.manageVacation

import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
    toggleState: State<Boolean>,
    inputFieldStatusState: State<InputFieldStatus>,
    onHalfVacationClick: () -> Unit,
    vacationState: TextFieldState,
    modifier: Modifier = Modifier,

    ) {
    val focusRequester = remember { FocusRequester() }

    val onlyDigitVacationState = remember {
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
            hours = if (toggleState.value) 4 else 0,
            days = onlyDigitVacationState.value,
            vacationState = vacationState,
            focusRequester = focusRequester,
            onKeyboardAction = {
                if (inputFieldStatusState.value != InputFieldStatus.ERROR) {
                    focusManager.clearFocus()
                }

            },
            onHalfVacationClick = onHalfVacationClick,
            inputFieldStatus = inputFieldStatusState.value,
            isToggleOn = toggleState.value,
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
        val days = 15
        val toggleState = remember { mutableStateOf(false) }
        val vacationState: TextFieldState = rememberTextFieldState(initialText = days.toString())
        val inputFieldStatusState = remember {
            derivedStateOf {
                val vacationStateText = vacationState.text.toString()
                if (checkIsAllDigit(vacationStateText)) {
                    InputFieldStatus.DEFAULT
                } else {
                    InputFieldStatus.ERROR
                }
            }
        }


        ManageVacationScreen(
            modifier = Modifier.fillMaxSize(),
            onClickBackButton = {},
            onClickSaveButton = {},
            onHalfVacationClick = {
                toggleState.value = toggleState.value.not()
            },
            toggleState = toggleState,
            vacationState = vacationState,
            inputFieldStatusState = inputFieldStatusState,
        )
    }
}
