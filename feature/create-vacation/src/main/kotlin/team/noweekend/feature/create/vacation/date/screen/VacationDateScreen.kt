package team.noweekend.feature.create.vacation.date.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.noweekend.core.design.system.core.component.button.defaults.BoxButtonType
import team.noweekend.core.design.system.core.component.button.fill.NWKFillButton
import team.noweekend.core.design.system.core.component.icon.NWKIcon
import team.noweekend.core.design.system.core.component.scaffold.NWKScaffold
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.core.resource.NWKDrawableResource
import team.noweekend.core.resource.NWKStringResource
import team.noweekend.feature.create.vacation.date.mvi.VacationDateUiState

@Composable
internal fun VacationDateScreen(
    uiState: VacationDateUiState,
    onBackClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    NWKScaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 16.dp),
            ) {
                NWKIcon(
                    resourceId = NWKDrawableResource.ChevronLeft,
                    modifier = Modifier.size(24.dp).clickable(onClick = onBackClick),
                    tint = NWKTheme.color.Semantic.Text.body,
                )
            }
        },
        content = { paddingValues ->
            VacationDateScreenContent(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                remainedDays = uiState.remainedDays,
                usageDays = uiState.usageDays,
            )
        },
        bottomBar = {
            NWKFillButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 20.dp,
                        vertical = 8.dp,
                    ),
                onClick = onNextClick,
                text = "다음",
                type = BoxButtonType.BLACK,
                enabled = uiState.isButtonEnabled
            )
        },
    )
}

@Composable
private fun VacationDateScreenContent(
    usageDays: Int,
    remainedDays: Int?,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(Modifier.size(NWKTheme.spacing.space600))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(NWKStringResource.CreateVacationDateHeader),
            style = NWKTheme.typography.heading2.copy(
                fontWeight = FontWeight.W700,
                color = NWKTheme.color.Semantic.Text.neutral,
            ),
            textAlign = TextAlign.Center,
        )
        Spacer(Modifier.size(NWKTheme.spacing.space50))
        remainedDays?.let { days ->
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(NWKStringResource.CreateVacationDateRemainedDaysDescription, days),
                style = NWKTheme.typography.body1.copy(
                    fontWeight = FontWeight.W500,
                    color = NWKTheme.color.Semantic.Text.body,
                ),
                textAlign = TextAlign.Center,
            )
        }
        Spacer(Modifier.size(NWKTheme.spacing.space500))
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = usageDays.toString(),
            onValueChange = {},
        )
    }
}

@Preview
@Composable
private fun VacationDateScreenPreview() {
    NWKTheme {
        VacationDateScreen(
            uiState = VacationDateUiState.INITIAL_STATE.copy(remainedDays = 7, usageDays = 0),
            onBackClick = {},
            onNextClick = {},
        )
    }
}
