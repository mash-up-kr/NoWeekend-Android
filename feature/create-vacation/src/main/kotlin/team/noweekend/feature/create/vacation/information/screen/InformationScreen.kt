package team.noweekend.feature.create.vacation.information.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.ImmutableMap
import team.noweekend.core.design.system.core.component.button.defaults.BoxButtonType
import team.noweekend.core.design.system.core.component.button.fill.NWKFillButton
import team.noweekend.core.design.system.core.component.icon.NWKIcon
import team.noweekend.core.design.system.core.component.scaffold.NWKScaffold
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.core.resource.NWKDrawableResource
import team.noweekend.core.resource.NWKStringResource
import team.noweekend.feature.create.vacation.information.component.button.InformationSelectRadioGroupContent
import team.noweekend.feature.create.vacation.information.model.InformationRadioGroupUiModel
import team.noweekend.feature.create.vacation.information.mvi.InformationUiState

@Composable
internal fun InformationScreen(
    uiState: State<InformationUiState>,
    onBackClick: () -> Unit,
    onBbassakCreateClick: () -> Unit,
    selectInformation: (Int, InformationRadioGroupUiModel) -> Unit,
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
                    modifier = Modifier
                        .size(24.dp)
                        .clickable(onClick = onBackClick),
                    tint = NWKTheme.color.Semantic.Text.body,
                )
            }
        },
        content = { paddingValues ->
            InformationScreenContent(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                informationData = uiState.value.informationData,
                selectInformation = selectInformation,
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
                onClick = onBbassakCreateClick,
                text = "휴가 빠삭하게 굽기",
                type = BoxButtonType.BLACK,
                enabled = uiState.value.isButtonEnabled.value,
            )
        },
    )
}

@Composable
private fun InformationScreenContent(
    informationData: ImmutableMap<Int, ImmutableList<InformationRadioGroupUiModel>>,
    selectInformation: (Int, InformationRadioGroupUiModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState()),
    ) {
        Spacer(Modifier.size(NWKTheme.spacing.space600))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(NWKStringResource.CreateVacationInformationHeader),
            style = NWKTheme.typography.heading2.copy(
                fontWeight = FontWeight.W700,
                color = NWKTheme.color.Semantic.Text.neutral,
            ),
            textAlign = TextAlign.Center,
        )
        Spacer(Modifier.size(NWKTheme.spacing.space50))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(NWKStringResource.CreateVacationInformationDescription),
            style = NWKTheme.typography.body1.copy(
                fontWeight = FontWeight.W500,
                color = NWKTheme.color.Semantic.Text.body,
            ),
            textAlign = TextAlign.Center,
        )
        Spacer(Modifier.size(NWKTheme.spacing.space500))
        InformationSelectRadioGroupContent(
            informationData = informationData,
            selectInformation = selectInformation,
        )
    }
}

@Preview
@Composable
private fun InformationScreenPreview() {
    val uiState = remember { mutableStateOf(InformationUiState.DUMMY_STATE) }

    NWKTheme {
        InformationScreen(
            uiState = uiState,
            onBackClick = {},
            onBbassakCreateClick = {},
            selectInformation = { _, _ -> },
        )
    }
}
