package team.noweekend.core.common.ui.vacation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.noweekend.core.design.system.core.component.input.NWKInputField
import team.noweekend.core.design.system.core.component.input.status.TextInputType
import team.noweekend.core.design.system.core.component.toggle.Toggle
import team.noweekend.core.design.system.foundation.theme.NWKTheme

@Composable
fun ColumnScope.RemainedVacation(
    days: Int,
    hours: Int,
    vacationState: TextFieldState,
    focusRequester: FocusRequester,
    onKeyboardAction: () -> Unit,
    onHalfVacationClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = "올해 남은 연차를 알려주세요",
        style = NWKTheme.typography.heading2.copy(
            fontWeight = FontWeight.W700,
            color = NWKTheme.color.Neutral.neutralGray900,
        ),
    )
    Spacer(modifier = Modifier.size(NWKTheme.spacing.space400))
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = "${days}일 ${hours}시간",
        style = NWKTheme.typography.heading2.copy(
            fontWeight = FontWeight.W700,
            color = NWKTheme.color.Toast.toast500,
        ),
        textAlign = TextAlign.Center,
    )
    Spacer(modifier = Modifier.size(NWKTheme.spacing.space400))
    NWKInputField(
        label = "남은 연차",
        placeholder = "0",
        errorText = "",
        textFieldState = vacationState,
        onKeyboardAction = onKeyboardAction,
        modifier = modifier,
        focusRequester = focusRequester,
        textInputType = TextInputType.DAY,
        keyboardImeAction = ImeAction.Done,
    )
    Spacer(modifier = Modifier.size(24.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = "반차도 남았어요.",
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
private fun RemainedVacationPreview() {
    NWKTheme {
        Column(modifier = Modifier.background(NWKTheme.color.Neutral.white)) {
            RemainedVacation(
                days = 15,
                hours = 4,
                vacationState = TextFieldState(),
                focusRequester = FocusRequester(),
                onKeyboardAction = {},
                onHalfVacationClick = {},
            )
        }
    }
}
