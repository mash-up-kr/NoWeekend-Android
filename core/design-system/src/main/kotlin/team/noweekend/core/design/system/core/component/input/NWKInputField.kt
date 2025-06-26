package team.noweekend.core.design.system.core.component.input

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.clearText
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.noweekend.core.common.kotlin.model.TextInputType
import team.noweekend.core.design.system.core.component.image.NWKImage
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.core.resource.NWKDrawableResource
import team.noweekend.core.resource.NWKStringResource

@Composable
fun NWKInputField(
    onClickKeyboardEnter: () -> Unit,
    textFieldState: TextFieldState,
    modifier: Modifier = Modifier,
    textInputType: TextInputType = TextInputType.TEXT,
    textHint: String = "",
    isSingLine: Boolean = true,
    isError: Boolean = false,
    errorText: String = "",
    keyboardType: KeyboardType = KeyboardType.Text,
) {
    val lineColor = if (isError) {
        NWKTheme.color.Toast.toast500
    } else {
        if (textFieldState.text.isEmpty()) {
            NWKTheme.color.Semantic.Border.border02
        } else {
            NWKTheme.color.Semantic.Text.neutral
        }
    }

    Column(modifier = modifier) {
        BasicTextField(
            modifier = Modifier
                .fillMaxWidth()
                .drawBehind {
                    drawLine(
                        color = lineColor,
                        start = Offset(0f, size.height - 1f),
                        end = Offset(size.width, size.height - 1f),
                        strokeWidth = 1f,
                    )
                },
            state = textFieldState,
            lineLimits = if (isSingLine) TextFieldLineLimits.SingleLine else TextFieldLineLimits.Default,
            textStyle = NWKTheme.typography.body1,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            onKeyboardAction = { onClickKeyboardEnter() },
            cursorBrush = SolidColor(NWKTheme.color.Neutral.black),
            decorator = { innerTextField ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                ) {
                    if (textFieldState.text.isEmpty()) {
                        Text(
                            modifier = Modifier.align(Alignment.CenterStart),
                            text = textHint,
                            color = NWKTheme.color.Neutral.neutralGray700,
                        )
                    }
                    Row(
                        modifier = Modifier,
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Row(modifier = Modifier.weight(9f)) {
                            innerTextField()
                        }
                        TailContent(
                            modifier = Modifier.padding(start = 12.dp),
                            textFieldState = textFieldState,
                            textInputType = textInputType,
                        )
                    }
                }
            },
        )
        if (isError && errorText.isNotEmpty() && textFieldState.text.isEmpty()) {
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = errorText,
                color = NWKTheme.color.Toast.toast700,
                style = NWKTheme.typography.body2,
            )
        }
    }
}

@Composable
private fun TailContent(textFieldState: TextFieldState, textInputType: TextInputType, modifier: Modifier = Modifier) {
    when (textInputType) {
        TextInputType.NICKNAME -> {
            TextClearIcon(
                textFieldState = textFieldState,
                textInputType = textInputType,
                modifier = modifier.size(22.dp),
            )
        }

        TextInputType.DATE -> {
            Text(
                modifier = modifier,
                text = stringResource(NWKStringResource.Day),
                color = NWKTheme.color.Semantic.Text.neutral,
                style = NWKTheme.typography.body1,
            )
        }

        TextInputType.TIME -> {
            Text(
                modifier = modifier,
                text = stringResource(NWKStringResource.Time),
                color = NWKTheme.color.Semantic.Text.neutral,
                style = NWKTheme.typography.body1,
            )
        }

        else -> Unit
    }
}

@Composable
private fun TextClearIcon(textInputType: TextInputType, textFieldState: TextFieldState, modifier: Modifier = Modifier) {
    if (textFieldState.text.isNotEmpty() && textInputType == TextInputType.NICKNAME) {
        NWKImage(
            modifier = modifier.clickable { textFieldState.clearText() },
            drawableResId = NWKDrawableResource.TextClear,
            alignment = Alignment.CenterEnd,
        )
    }
}

@Preview
@Composable
private fun NWKInputFieldPreview() {
    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.spacedBy(30.dp),
    ) {
        NWKInputField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            textFieldState = rememberTextFieldState(),
            textInputType = TextInputType.DATE,
            textHint = "0",
            isSingLine = true,
            isError = false,
            errorText = "",
            keyboardType = KeyboardType.Text,
            onClickKeyboardEnter = { Unit },
        )
        val textFieldState = rememberTextFieldState()
        NWKInputField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            textFieldState = textFieldState,
            textInputType = TextInputType.NICKNAME,
            textHint = "닉네임을 입력해주세요",
            isSingLine = true,
            isError = textFieldState.text.isEmpty(),
            errorText = "0자 이상 입력해주세요",
            keyboardType = KeyboardType.Text,
            onClickKeyboardEnter = { Unit },
        )
        NWKInputField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            textFieldState = rememberTextFieldState(),
            textInputType = TextInputType.TIME,
            textHint = "시간을 입력해주세요",
            isSingLine = true,
            isError = false,
            errorText = "",
            keyboardType = KeyboardType.Text,
            onClickKeyboardEnter = { Unit },
        )
        NWKInputField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            textFieldState = rememberTextFieldState(),
            textInputType = TextInputType.TEXT,
            textHint = "제목",
            isSingLine = false,
            isError = false,
            errorText = "",
            keyboardType = KeyboardType.Text,
            onClickKeyboardEnter = { Unit },
        )
    }
}
