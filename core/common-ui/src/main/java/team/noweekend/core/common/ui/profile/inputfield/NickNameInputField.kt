package team.noweekend.core.common.ui.profile.inputfield

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.text.input.ImeAction
import team.noweekend.core.design.system.core.component.input.NWKInputField
import team.noweekend.core.design.system.core.component.input.status.InputFieldStatus
import team.noweekend.core.design.system.core.component.input.status.TextInputType

@Composable
internal fun NickNameInputField(
    textFieldState: TextFieldState,
    onKeyboardAction: () -> Unit,
    focusRequester: FocusRequester,
    inputFieldStatus: InputFieldStatus,
    modifier: Modifier = Modifier,
) {
    NWKInputField(
        label = "닉네임",
        placeholder = "1~6자 이내, 영문 숫자 사용 가능",
        errorText = "6글까지 작성할 수 있어요.",
        textFieldState = textFieldState,
        onKeyboardAction = onKeyboardAction,
        modifier = modifier.fillMaxWidth(),
        inputFieldStatus = inputFieldStatus,
        focusRequester = focusRequester,
        textInputType = TextInputType.NICKNAME,
        keyboardImeAction = ImeAction.Next,
    )
}
