package team.noweekend.feature.onboarding.vacation.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.noweekend.core.common.ui.vacation.RemainedVacationComponent
import team.noweekend.core.design.system.core.component.button.defaults.BoxButtonType
import team.noweekend.core.design.system.core.component.button.fill.NWKFillButton
import team.noweekend.core.design.system.core.component.header.NWKHeader
import team.noweekend.core.design.system.core.component.scaffold.NWKScaffold
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.core.resource.NWKStringResource

@Composable
internal fun RemainedVacationInputScreen(
    onBackClick: () -> Unit,
    onConfirmClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    NWKScaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            NWKHeader(
                onBackClick = onBackClick,
                text = "2/3",
            )
        },
        content = {
            RemainedVacationInputScreenContent(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                days = 10,
                hours = 10,
                onHalfVacationClick = {},
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
private fun RemainedVacationInputScreenContent(
    days: Int,
    hours: Int,
    onHalfVacationClick: () -> Unit,
    modifier: Modifier = Modifier,
    vacationState: TextFieldState = TextFieldState(),
) {
    val focusRequester: FocusRequester = remember { FocusRequester() }
    val focusManager: FocusManager = LocalFocusManager.current

    RemainedVacationComponent(
        days = days,
        hours = hours,
        vacationState = vacationState,
        focusRequester = focusRequester,
        onKeyboardAction = {
            focusManager.clearFocus()
        },
        onHalfVacationClick = onHalfVacationClick,
        modifier = modifier,
    )
}

@Preview
@Composable
private fun RemainedVacationInputScreenPreview() {
    NWKTheme {
        RemainedVacationInputScreen(
            onBackClick = {},
            onConfirmClick = {},
            modifier = Modifier.fillMaxSize(),
        )
    }
}
