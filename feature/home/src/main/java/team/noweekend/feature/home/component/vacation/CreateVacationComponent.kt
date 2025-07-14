package team.noweekend.feature.home.component.vacation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.noweekend.core.common.android.extension.fillMaxWidthOfScreen
import team.noweekend.core.design.system.core.component.button.defaults.BoxButtonType
import team.noweekend.core.design.system.core.component.button.fill.NWKFillButton
import team.noweekend.core.design.system.core.component.image.NWKImage
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.core.resource.NWKStringResource
import team.noweekend.feature.home.mvi.CreateVacationStatus

internal fun LazyListScope.createVacation(
    temperature: Int,
    maximumVacation: Int,
    createVacationStatus: CreateVacationStatus,
    onCreateVacationClick: () -> Unit,
    modifier: Modifier = Modifier,
) = item {
    CreateVacationComponent(
        temperature = temperature,
        maximumVacation = maximumVacation,
        createVacationStatus = createVacationStatus,
        onCreateVacationClick = onCreateVacationClick,
        modifier = modifier,
    )
}

@Composable
internal fun CreateVacationComponent(
    temperature: Int,
    maximumVacation: Int,
    createVacationStatus: CreateVacationStatus,
    onCreateVacationClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidthOfScreen(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AverageTemperature(
            temperature = temperature,
            guideMessageResId = createVacationStatus.messageResourceId,
        )
        NWKImage(
            drawableResId = createVacationStatus.imageResourceId,
        )
        NWKFillButton(
            onClick = onCreateVacationClick,
            text = stringResource(NWKStringResource.HomeCreateVacationButtonText, maximumVacation),
            type = BoxButtonType.PRIMARY,
        )
    }
}

@Composable
private fun AverageTemperature(
    temperature: Int,
    guideMessageResId: Int,
    modifier: Modifier = Modifier,
) {
    val message: AnnotatedString = buildAnnotatedString {
        append(stringResource(NWKStringResource.HomeAverageTemperatureTextSpan1))
        withStyle(style = SpanStyle(NWKTheme.color.Toast.toast500)) {
            append(stringResource(NWKStringResource.HomeAverageTemperatureTextSpan2, temperature))
        }
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = message,
            style = NWKTheme.typography.body1.copy(
                fontWeight = FontWeight.W500,
                color = NWKTheme.color.Semantic.Text.body,
            ),
            textAlign = TextAlign.Center,
        )

        Text(
            text = stringResource(guideMessageResId),
            style = NWKTheme.typography.heading4.copy(
                fontWeight = FontWeight.W700,
                color = NWKTheme.color.Semantic.Text.neutral,
            ),
            textAlign = TextAlign.Center,
        )
    }
}

@Preview
@Composable
private fun CreateVacationComponentPreview() {
    NWKTheme {
        CreateVacationComponent(
            temperature = 97,
            maximumVacation = 3,
            createVacationStatus = CreateVacationStatus.IN_PROGRESS,
            onCreateVacationClick = {},
        )
    }
}
