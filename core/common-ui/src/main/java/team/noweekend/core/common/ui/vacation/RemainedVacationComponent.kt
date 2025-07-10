package team.noweekend.core.common.ui.vacation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.noweekend.core.common.ui.vacation.input.RemainedVacationInputField
import team.noweekend.core.design.system.core.component.toggle.Toggle
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.core.resource.NWKStringResource

@Composable
fun RemainedVacationComponent(
    days: Int,
    hours: Int,
    vacationState: TextFieldState,
    focusRequester: FocusRequester,
    onKeyboardAction: () -> Unit,
    onHalfVacationClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        RemainedVacationHeader(
            days = days,
            hours = hours,
        )
        Spacer(modifier = Modifier.size(NWKTheme.spacing.space400))
        RemainedVacationInputField(
            vacationState = vacationState,
            onKeyboardAction = onKeyboardAction,
            focusRequester = focusRequester,
        )
        Spacer(modifier = Modifier.size(24.dp))
        RemainedHalfVacationToggle(
            onHalfVacationClick = onHalfVacationClick,
        )
    }
}

@Composable
private fun RemainedHalfVacationToggle(
    onHalfVacationClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = stringResource(NWKStringResource.RemainedHalfVacationDescription),
            style = NWKTheme.typography.body1.copy(
                fontWeight = FontWeight.W500,
                color = NWKTheme.color.Semantic.Text.body,
            ),
        )
        Toggle(
            onClickToggle = onHalfVacationClick,
        )
    }
}

@Preview
@Composable
private fun RemainedVacationComponentPreview() {
    NWKTheme {
        RemainedVacationComponent(
            modifier = Modifier.background(NWKTheme.color.Neutral.white),
            days = 15,
            hours = 4,
            vacationState = TextFieldState(),
            focusRequester = FocusRequester(),
            onKeyboardAction = {},
            onHalfVacationClick = {},
        )
    }
}
