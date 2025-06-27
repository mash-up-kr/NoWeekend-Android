package team.noweekend.feature.onboarding.nickname.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.noweekend.core.common.kotlin.model.TextInputType
import team.noweekend.core.design.system.core.component.button.defaults.BoxButtonType
import team.noweekend.core.design.system.core.component.button.fill.NWKFillButton
import team.noweekend.core.design.system.core.component.header.NWKHeader
import team.noweekend.core.design.system.core.component.input.NWKInputField
import team.noweekend.core.design.system.core.component.scaffold.NWKScaffold
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.core.resource.NWKStringResource

@Composable
fun NicknameInputScreen(
    onClickBack: () -> Unit,
    onClickConfirm: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val textFieldState = rememberTextFieldState()
    val keyboardController = LocalSoftwareKeyboardController.current

    NWKScaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            NWKHeader(
                onClickBack = onClickBack,
                title = "1/3", // TODO : Replace with actual title
                modifier = Modifier.fillMaxWidth(),
            )
        },
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = it)
                .padding(top = 24.dp, start = 20.dp, end = 20.dp),
        ) {
            Column(modifier = Modifier.align(Alignment.TopCenter)) {
                TitleContainer(modifier = Modifier.fillMaxWidth())
                NWKInputField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp),
                    textFieldState = textFieldState,
                    textInputType = TextInputType.NICKNAME,
                    textHint = stringResource(NWKStringResource.OnboardingNicknameInputHint),
                    isSingLine = true,
                    onClickKeyboardEnter = { keyboardController?.hide() },
                )
            }
            NWKFillButton(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 29.dp)
                    .fillMaxWidth(),
                onClick = {},
                text = stringResource(NWKStringResource.Next),
                type = BoxButtonType.BLACK,
                enabled = textFieldState.text.isNotEmpty(),
            )
        }
    }
}

@Composable
private fun TitleContainer(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = stringResource(NWKStringResource.OnboardingNicknameTitle),
            color = NWKTheme.color.Neutral.neutralGray900,
            style = NWKTheme.typography.heading2,
        )
        Text(
            modifier = Modifier.padding(top = 4.dp),
            text = stringResource(NWKStringResource.OnboardingNicknameSubTitle),
            style = NWKTheme.typography.body1,
        )
    }
}

@Preview
@Composable
private fun NicknameInputScreenPreview() {
    NWKTheme {
        NicknameInputScreen(
            onClickBack = { Unit },
            modifier = Modifier.fillMaxSize(),
            onClickConfirm = { Unit },
        )
    }
}
