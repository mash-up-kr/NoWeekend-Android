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
        inputTransformation = {
            val numericText = asCharSequence().filter { it.isDigit() }
            // 숫자 범위 (0~100)로 제한
            val limitedValue = numericText.toString().toIntOrNull()?.coerceIn(0, 100)
            // 최종 값 설정 (null일 경우 빈칸)
            val result = limitedValue?.toString() ?: ""
            // 입력 필드에 최종 결과를 적용
            replace(0, length, result)
        },
        keyboardImeAction = ImeAction.Done,
    )
}
