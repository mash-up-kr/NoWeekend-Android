package team.noweekend.feature.profile.manageVacation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import team.noweekend.core.design.system.core.component.input.status.InputFieldStatus

@Composable
fun ManageVacationRoute(
    modifier: Modifier = Modifier,
) {

    val days = 15
    val toggleState =remember { mutableStateOf(false) }
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
        toggleState = toggleState,
        onClickBackButton = {},
        onClickSaveButton = {},
        onHalfVacationClick = {
            toggleState.value = toggleState.value.not()
        },
        vacationState =vacationState,
        inputFieldStatusState = inputFieldStatusState
    )
}
