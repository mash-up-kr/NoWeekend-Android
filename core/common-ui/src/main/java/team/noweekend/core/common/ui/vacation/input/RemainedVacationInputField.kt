package team.noweekend.core.common.ui.vacation.input

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import team.noweekend.core.design.system.core.component.input.NWKInputField
import team.noweekend.core.design.system.core.component.input.status.InputFieldStatus
import team.noweekend.core.design.system.core.component.input.status.TextInputType
import team.noweekend.core.resource.NWKStringResource

@Composable
fun RemainedVacationInputField(
    vacationState: TextFieldState,
    onKeyboardAction: () -> Unit,
    modifier: Modifier = Modifier,
    errorText: String = "",
    inputTextFieldStatus: InputFieldStatus = InputFieldStatus.DEFAULT,
    focusRequester: FocusRequester = remember { FocusRequester() },
) {
    NWKInputField(
        label = stringResource(NWKStringResource.RemainedVacationInputLabel),
        placeholder = stringResource(NWKStringResource.RemainedVacationInputPlaceholder),
        errorText = errorText,
        textFieldState = vacationState,
        onKeyboardAction = onKeyboardAction,
        modifier = modifier.fillMaxWidth(),
        focusRequester = focusRequester,
        textInputType = TextInputType.DAY,
        inputFieldStatus = inputTextFieldStatus,
        keyboardImeAction = ImeAction.Done,
    )
}
