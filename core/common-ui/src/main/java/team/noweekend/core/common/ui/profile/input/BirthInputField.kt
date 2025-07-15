package team.noweekend.core.common.ui.profile.input

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.core.text.isDigitsOnly
import team.noweekend.core.common.kotlin.util.ValidateUtils
import team.noweekend.core.design.system.core.component.input.NWKInputField
import team.noweekend.core.design.system.core.component.input.status.InputFieldStatus
import team.noweekend.core.design.system.core.component.input.status.TextInputType
import team.noweekend.core.resource.NWKStringResource

@Composable
fun BirthInputField(
    textFieldState: TextFieldState,
    onKeyboardAction: () -> Unit,
    inputFieldStatus: InputFieldStatus,
    onValidateBirthState: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    focusRequester: FocusRequester = remember { FocusRequester() },
) {
    val errorResId = checkErrorMessage(textFieldState.text.toString())
    val isValid = errorResId == null

    LaunchedEffect(isValid) {
        onValidateBirthState(isValid)
    }

    NWKInputField(
        label = stringResource(NWKStringResource.BirthInputLabel),
        placeholder = stringResource(NWKStringResource.BirthInputPlaceholder),
        errorText = if (!isValid) stringResource(errorResId!!) else "",
        textFieldState = textFieldState,
        onKeyboardAction = onKeyboardAction,
        modifier = modifier,
        inputFieldStatus = if (!isValid && textFieldState.text.isNotEmpty()) {
            InputFieldStatus.ERROR
        } else {
            InputFieldStatus.DEFAULT
        },
        focusRequester = focusRequester,
        textInputType = TextInputType.NUMBER,
        keyboardImeAction = ImeAction.Done,
    )
}

private fun checkErrorMessage(inputText: String): Int? {
    return when {
        inputText.length > 8 -> NWKStringResource.BirthInputLengthErrorMessage
        !inputText.isDigitsOnly() -> NWKStringResource.BirthInputNumberErrorMessage
        !ValidateUtils.isValidBirthday(inputText) -> NWKStringResource.BirthInputErrorMessage
        else -> null
    }
}
