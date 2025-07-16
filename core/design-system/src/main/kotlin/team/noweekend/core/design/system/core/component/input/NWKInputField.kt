package team.noweekend.core.design.system.core.component.input

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import team.noweekend.core.design.system.core.component.input.atomics.NWKTextFieldLabel
import team.noweekend.core.design.system.core.component.input.atomics.TrailingContent
import team.noweekend.core.design.system.core.component.input.status.InputFieldStatus
import team.noweekend.core.design.system.core.component.input.status.TextInputType
import team.noweekend.core.design.system.foundation.theme.NWKTheme

@Composable
fun NWKInputField(
    textFieldState: TextFieldState,
    onKeyboardAction: () -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    isUnderLine: Boolean = true,
    underLinePadding: Dp = 8.dp,
    inputTransformation: InputTransformation? = null,
    errorText: String = "",
    label: String? = null,
    inputFieldStatus: InputFieldStatus = InputFieldStatus.DEFAULT,
    focusRequester: FocusRequester = FocusRequester(),
    textInputType: TextInputType = TextInputType.TEXT,
    keyboardImeAction: ImeAction = ImeAction.Default,
    isSingLine: Boolean = true,
) {
    val lineColor: Color = when {
        inputFieldStatus == InputFieldStatus.ERROR -> NWKTheme.color.Toast.toast500
        textFieldState.text.isEmpty() -> NWKTheme.color.Semantic.Border.border02
        else -> NWKTheme.color.Semantic.Text.neutral
    }

    Column(modifier = modifier) {
        label?.let {
            NWKTextFieldLabel(
                text = label,
            )
        }
        BasicTextField(
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester)
                .then(
                    if (isUnderLine) {
                        Modifier.padding(bottom = underLinePadding)
                    } else {
                        Modifier
                    },
                )
                .drawBehind {
                    if (isUnderLine) {
                        drawLine(
                            color = lineColor,
                            start = Offset(0f, size.height - 1f),
                            end = Offset(size.width, size.height - 1f),
                            strokeWidth = 1.dp.toPx(),
                        )
                    }
                }
                .padding(bottom = underLinePadding),
            state = textFieldState,
            lineLimits = if (isSingLine) TextFieldLineLimits.SingleLine else TextFieldLineLimits.Default,
            textStyle = NWKTheme.typography.body1.copy(
                color = NWKTheme.color.Neutral.neutralGray900,
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = keyboardImeAction,
                keyboardType = textInputType.keyboardType,
            ),
            inputTransformation = inputTransformation,
            onKeyboardAction = { onKeyboardAction() },
            cursorBrush = SolidColor(NWKTheme.color.Neutral.black),
            decorator = { innerTextField ->
                Box(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    if (textFieldState.text.isEmpty() && placeholder.isNotEmpty()) {
                        Text(
                            modifier = Modifier.align(Alignment.CenterStart),
                            text = placeholder,
                            style = NWKTheme.typography.body1.copy(
                                fontWeight = FontWeight.W500,
                                color = NWKTheme.color.Semantic.Text.disabled,
                            ),
                        )
                    } else {
                        Row(
                            modifier = Modifier,
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                        ) {
                            Row(modifier = Modifier.weight(1f)) {
                                innerTextField()
                            }
                            TrailingContent(
                                textFieldState = textFieldState,
                                textInputType = textInputType,
                            )
                        }
                    }
                }
            },
        )
        if (inputFieldStatus == InputFieldStatus.ERROR && errorText.isNotEmpty()) {
            Spacer(Modifier.size(8.dp))
            Text(
                modifier = Modifier,
                text = errorText,
                style = NWKTheme.typography.body2.copy(
                    color = NWKTheme.color.Toast.toast700,
                ),
            )
        }
    }
}

@Preview
@Composable
private fun NWKInputFieldPreview() {
    Column(
        modifier = Modifier.background(NWKTheme.color.Neutral.white),
        verticalArrangement = Arrangement.spacedBy(30.dp),
    ) {
        NWKInputField(
            label = "라벨",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            textFieldState = rememberTextFieldState(),
            textInputType = TextInputType.DAY,
            placeholder = "0",
            isSingLine = true,
            errorText = "",
            onKeyboardAction = { Unit },
        )
        val textFieldState = rememberTextFieldState()
        NWKInputField(
            label = "라벨",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            textFieldState = textFieldState,
            textInputType = TextInputType.NICKNAME,
            placeholder = "닉네임을 입력해주세요",
            isSingLine = true,
            inputFieldStatus = InputFieldStatus.ERROR,
            errorText = "0자 이상 입력해주세요",
            onKeyboardAction = { Unit },
        )
        NWKInputField(
            label = "라벨",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            textFieldState = rememberTextFieldState(),
            textInputType = TextInputType.TIME,
            placeholder = "시간을 입력해주세요",
            isSingLine = true,
            errorText = "",
            onKeyboardAction = { Unit },
        )
        NWKInputField(
            label = "라벨",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            textFieldState = rememberTextFieldState(),
            textInputType = TextInputType.TEXT,
            placeholder = "제목",
            isSingLine = false,
            inputFieldStatus = InputFieldStatus.ERROR,
            errorText = "",
            onKeyboardAction = { Unit },
        )
    }
}
