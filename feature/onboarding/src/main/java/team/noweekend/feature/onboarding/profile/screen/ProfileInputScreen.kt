package team.noweekend.feature.onboarding.profile.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
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
import team.noweekend.feature.onboarding.mvi.OnboardUiState

@Composable
internal fun ProfileInputScreen(
    onBackClick: () -> Unit,
    onConfirmClick: (String, String) -> Unit,
    uiState: OnboardUiState,
    modifier: Modifier = Modifier,
) {
    val nicknameFieldState = rememberTextFieldState()
    val birthFieldState = rememberTextFieldState()
    var validateNicknameState by remember { mutableStateOf(false) }
    var validateBirthState by remember { mutableStateOf(false) }

    NWKScaffold(
        modifier = modifier.imePadding(),
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
                nicknameTextFieldState = nicknameFieldState,
                birthTextFieldState = birthFieldState,
                onValidateNicknameState = {
                    validateNicknameState = it
                },
                onValidateBirthState = {
                    validateBirthState = it
                },
            )
        },
        bottomBar = {
            NWKFillButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 8.dp),
                onClick = {
                    onConfirmClick(
                        nicknameFieldState.text.toString(),
                        birthFieldState.text.toString(),
                    )
                },
                text = stringResource(NWKStringResource.Next),
                type = BoxButtonType.BLACK,
                enabled = validateBirthState &&
                    validateNicknameState &&
                    nicknameFieldState.text.isNotEmpty() &&
                    birthFieldState.text.isNotEmpty(),
            )
        },
    )
}

@Composable
private fun ProfileInputScreenContent(
    nicknameTextFieldState: TextFieldState,
    birthTextFieldState: TextFieldState,
    onValidateNicknameState: (Boolean) -> Unit,
    onValidateBirthState: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    nickNameInputFieldStatus: InputFieldStatus = InputFieldStatus.DEFAULT,
    birthInputFieldStatus: InputFieldStatus = InputFieldStatus.DEFAULT,
) {
    ProfileInputComponent(
        modifier = modifier,
        nicknameTextFieldState = nicknameTextFieldState,
        birthTextFieldState = birthTextFieldState,
        nickNameInputFieldStatus = nickNameInputFieldStatus,
        birthInputFieldStatus = birthInputFieldStatus,
        onValidateNicknameState = onValidateNicknameState,
        onValidateBirthState = onValidateBirthState,
    )
}

@Preview
@Composable
private fun ProfileInputScreenPreview() {
    NWKTheme {
        ProfileInputScreen(
            onBackClick = {},
            onConfirmClick = { _, _ -> },
            uiState = OnboardUiState.INITIAL_STATE,
        )
    }
}
