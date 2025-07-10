package team.noweekend.core.common.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.noweekend.core.common.ui.profile.input.BirthInputField
import team.noweekend.core.common.ui.profile.input.NickNameInputField
import team.noweekend.core.design.system.core.component.input.status.InputFieldStatus
import team.noweekend.core.design.system.foundation.theme.NWKTheme

@Composable
fun ProfileInputComponent(
    nickName: TextFieldState,
    birth: TextFieldState,
    nickNameInputFieldStatus: InputFieldStatus,
    birthInputFieldStatus: InputFieldStatus,
    modifier: Modifier = Modifier,
) {
    val focusManager: FocusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(key1 = Unit) {
        focusRequester.requestFocus()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.size(48.dp))
        ProfileInputScreenHeader()
        Spacer(modifier = Modifier.size(NWKTheme.spacing.space500))
        NickNameInputField(
            textFieldState = nickName,
            inputFieldStatus = nickNameInputFieldStatus,
            focusRequester = focusRequester,
            onKeyboardAction = { focusManager.moveFocus(FocusDirection.Down) },
        )
        Spacer(modifier = Modifier.size(24.dp))
        BirthInputField(
            textFieldState = birth,
            inputFieldStatus = birthInputFieldStatus,
            onKeyboardAction = { focusManager.clearFocus() },
        )
    }
}

@Preview
@Composable
private fun ProfileInputComponentPreview() {
    NWKTheme {
        ProfileInputComponent(
            nickName = TextFieldState(),
            birth = TextFieldState(),
            nickNameInputFieldStatus = InputFieldStatus.ERROR,
            birthInputFieldStatus = InputFieldStatus.ERROR,
            modifier = Modifier.background(NWKTheme.color.Neutral.white),
        )
    }
}
