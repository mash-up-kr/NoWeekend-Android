package team.noweekend.feature.onboarding.vacation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.noweekend.core.design.system.core.component.button.defaults.BoxButtonType
import team.noweekend.core.design.system.core.component.button.fill.NWKFillButton
import team.noweekend.core.design.system.core.component.header.NWKHeader
import team.noweekend.core.design.system.core.component.input.NWKInputField
import team.noweekend.core.design.system.core.component.input.status.TextInputType
import team.noweekend.core.design.system.core.component.scaffold.NWKScaffold
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.core.resource.NWKStringResource

@Composable
fun RemainedVacationInputScreen(
    onClickBack: () -> Unit,
    onClickConfirm: () -> Unit,
    modifier: Modifier = Modifier,
) {
    NWKScaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            NWKHeader(
                onBackClick = onClickBack,
                text = "2/3",
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
                VacationTitleContainer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 32.dp),
                    dayText = "0", // TODO: Replace with actual state or value
                    timeText = "0",
                    myVacation = "15", // TODO: Replace with actual state or value
                )
                VacationInputContainer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 32.dp),
                )
            }
            NWKFillButton(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 29.dp)
                    .fillMaxWidth(),
                onClick = onClickConfirm,
                text = stringResource(NWKStringResource.Next),
                type = BoxButtonType.PRIMARY,
                enabled = true, // TODO: Replace with actual enabled state based on input validation
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
            text = stringResource(NWKStringResource.VacationInputTitle),
            color = NWKTheme.color.Neutral.neutralGray900,
            style = NWKTheme.typography.heading2,
        )
        Text(
            modifier = Modifier.padding(top = 4.dp),
            text = stringResource(NWKStringResource.VacationInputSubTitle),
            style = NWKTheme.typography.body1,
        )
    }
}

@Composable
private fun VacationTitleContainer(
    dayText: String,
    timeText: String,
    myVacation: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = Modifier.alignByBaseline(),
            text = stringResource(NWKStringResource.VacationDayTimeFormat, dayText, timeText),
            color = NWKTheme.color.Toast.toast500,
            style = NWKTheme.typography.heading2,
        )
        Text(
            modifier = Modifier
                .padding(horizontal = 6.dp)
                .alignByBaseline(),
            text = stringResource(NWKStringResource.VacationSeparator),
            color = NWKTheme.color.Semantic.Text.neutral,
            style = NWKTheme.typography.body2,
        )
        Text(
            modifier = Modifier.alignByBaseline(),
            text = stringResource(NWKStringResource.VacationMyVacationFormat, myVacation),
            color = NWKTheme.color.Semantic.Text.neutral,
            style = NWKTheme.typography.body2,
        )
    }
}

@Composable
private fun VacationInputContainer(modifier: Modifier = Modifier) {
    val dayFieldState = rememberTextFieldState()
    val timeFieldState = rememberTextFieldState()
    val wholeVacationDayFieldState = rememberTextFieldState()

    Column(modifier = modifier) {
        Text(
            text = stringResource(NWKStringResource.RemainingVacationLabel),
            color = NWKTheme.color.Semantic.Text.body,
            style = NWKTheme.typography.subTitle1,
        )
        RemainingVacationInputContainer(
            modifier = Modifier.fillMaxWidth(),
            dayFieldState = dayFieldState,
            timeFieldState = timeFieldState,
        )
        ErrorTextContainer(
            modifier = Modifier.padding(top = 8.dp),
            isError = true, // TODO: Replace with actual error state based on input validation
        )
        Text(
            modifier = Modifier.padding(top = 24.dp),
            text = stringResource(NWKStringResource.AllVacationLabel),
            color = NWKTheme.color.Semantic.Text.body,
            style = NWKTheme.typography.subTitle1,
        )
        WholeVacationInputField(
            modifier = Modifier.fillMaxWidth(),
            wholeVacationDayFieldState = wholeVacationDayFieldState,
        )
    }
}

@Composable
private fun RemainingVacationInputContainer(
    dayFieldState: TextFieldState,
    timeFieldState: TextFieldState,
    modifier: Modifier = Modifier,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(
            24.dp,
            alignment = Alignment.CenterHorizontally,
        ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        NWKInputField(
            modifier = Modifier.weight(1f),
            textFieldState = dayFieldState,
            textInputType = TextInputType.DAY,
            keyboardType = KeyboardType.Number,
            placeholder = stringResource(NWKStringResource.DefaultDayNumber),
            isSingLine = true,
            onKeyboardAction = { keyboardController?.hide() },
        )
        NWKInputField(
            modifier = Modifier.weight(1f),
            textFieldState = timeFieldState,
            textInputType = TextInputType.TIME,
            keyboardType = KeyboardType.Number,
            placeholder = stringResource(NWKStringResource.DefaultDayNumber),
            isSingLine = true,
            onKeyboardAction = { keyboardController?.hide() },
        )
    }
}

@Composable
private fun ErrorTextContainer(isError: Boolean, modifier: Modifier = Modifier) {
    if (isError) {
        Row(modifier = modifier) {
            Text(
                text = stringResource(NWKStringResource.InputVacationError),
                color = NWKTheme.color.Toast.toast700,
                style = NWKTheme.typography.body2,
            )
        }
    }
}

@Composable
private fun WholeVacationInputField(wholeVacationDayFieldState: TextFieldState, modifier: Modifier = Modifier) {
    val keyboardController = LocalSoftwareKeyboardController.current

    NWKInputField(
        modifier = modifier,
        textFieldState = wholeVacationDayFieldState,
        textInputType = TextInputType.DAY,
        keyboardType = KeyboardType.Number,
        placeholder = stringResource(NWKStringResource.DefaultDayNumber),
        isSingLine = true,
        onKeyboardAction = { keyboardController?.hide() },
    )
}

@Preview
@Composable
private fun RemainedVacationInputScreenPreview() {
    NWKTheme {
        RemainedVacationInputScreen(
            onClickBack = {},
            onClickConfirm = {},
            modifier = Modifier.fillMaxSize(),
        )
    }
}
