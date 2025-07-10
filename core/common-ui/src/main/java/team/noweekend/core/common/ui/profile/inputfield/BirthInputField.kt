package team.noweekend.core.common.ui.profile.inputfield

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.text.input.ImeAction
import team.noweekend.core.design.system.core.component.input.NWKInputField
import team.noweekend.core.design.system.core.component.input.status.InputFieldStatus
import team.noweekend.core.design.system.core.component.input.status.TextInputType

@Composable
fun BirthInputField(
    textFieldState: TextFieldState,
    onKeyboardAction: () -> Unit,
    inputFieldStatus: InputFieldStatus,
    modifier: Modifier = Modifier,
    focusRequester: FocusRequester = FocusRequester(),
) {
    NWKInputField(
        label = "생년월일",
        placeholder = "예)19991213",
        errorText = "숫자만 입력할 수 있어요.",
        textFieldState = textFieldState,
        onKeyboardAction = onKeyboardAction,
        modifier = modifier,
        inputFieldStatus = inputFieldStatus,
        focusRequester = focusRequester,
        textInputType = TextInputType.DAY,
        keyboardImeAction = ImeAction.Done,
    )
}
