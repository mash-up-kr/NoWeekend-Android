package team.noweekend.core.common.ui.profile.input

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
fun NickNameInputField(
    textFieldState: TextFieldState,
    onKeyboardAction: () -> Unit,
    inputFieldStatus: InputFieldStatus,
    modifier: Modifier = Modifier,
    focusRequester: FocusRequester = remember { FocusRequester() },
) {
    NWKInputField(
        label = stringResource(NWKStringResource.NicknameInputLabel),
        placeholder = stringResource(NWKStringResource.NicknameInputPlaceholder),
        errorText = stringResource(NWKStringResource.NicknameInputErrorMessage),
        textFieldState = textFieldState,
        onKeyboardAction = onKeyboardAction,
        modifier = modifier.fillMaxWidth(),
        inputFieldStatus = inputFieldStatus,
        focusRequester = focusRequester,
        textInputType = TextInputType.NICKNAME,
        keyboardImeAction = ImeAction.Next,
    )
}
