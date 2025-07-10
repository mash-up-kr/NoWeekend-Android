package team.noweekend.feature.onboarding.profile.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.noweekend.core.common.ui.profile.ProfileInputComponent
import team.noweekend.core.design.system.core.component.button.defaults.BoxButtonType
import team.noweekend.core.design.system.core.component.button.fill.NWKFillButton
import team.noweekend.core.design.system.core.component.header.NWKHeader
import team.noweekend.core.design.system.core.component.input.status.InputFieldStatus
import team.noweekend.core.design.system.core.component.scaffold.NWKScaffold
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.core.resource.NWKStringResource

@Composable
fun ProfileInputScreen(
    onBackClick: () -> Unit,
    onConfirmClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val textFieldState = rememberTextFieldState()
    val keyboardController = LocalSoftwareKeyboardController.current

    NWKScaffold(
        modifier = modifier,
        topBar = {
            NWKHeader(
                text = "1/3",
                onBackClick = onBackClick,
            )
        },
        content = {
            ProfileInputScreenContent(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
            )
        },
        bottomBar = {
            NWKFillButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 8.dp),
                onClick = onConfirmClick,
                text = stringResource(NWKStringResource.Next),
                type = BoxButtonType.BLACK,
                enabled = true,
            )
        },
    )
}

@Composable
private fun ProfileInputScreenContent(
    modifier: Modifier = Modifier,
    nickName: TextFieldState = TextFieldState(),
    birth: TextFieldState = TextFieldState(),
    nickNameInputFieldStatus: InputFieldStatus = InputFieldStatus.DEFAULT,
    birthInputFieldStatus: InputFieldStatus = InputFieldStatus.DEFAULT,
) {
    ProfileInputComponent(
        modifier = modifier,
        nickName = nickName,
        birth = birth,
        nickNameInputFieldStatus = nickNameInputFieldStatus,
        birthInputFieldStatus = birthInputFieldStatus,
    )
}


@Preview
@Composable
private fun ProfileInputScreenPreview() {
    NWKTheme {
        ProfileInputScreen(
            onBackClick = {},
            onConfirmClick = {},
        )
    }
}
