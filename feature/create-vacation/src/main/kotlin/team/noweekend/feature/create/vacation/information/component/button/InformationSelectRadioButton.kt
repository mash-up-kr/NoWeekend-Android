package team.noweekend.feature.create.vacation.information.component.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.ImmutableMap
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.feature.create.vacation.information.component.button.defaults.InformationSelectRadioButtonColors
import team.noweekend.feature.create.vacation.information.component.button.defaults.InformationSelectRadioButtonDefaults
import team.noweekend.feature.create.vacation.information.component.button.preview.PreviewInformationSelectRadioButtonProvider
import team.noweekend.feature.create.vacation.information.component.button.preview.PreviewInformationSelectRadioGroupProvider
import team.noweekend.feature.create.vacation.information.model.InformationRadioGroupUiModel

@Composable
internal fun InformationSelectRadioGroupContent(
    informationData: ImmutableMap<Int, ImmutableList<InformationRadioGroupUiModel>>,
    selectInformation: (Int, InformationRadioGroupUiModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(NWKTheme.spacing.space300),
    ) {
        informationData.forEach { (rowIndex, informationRow) ->
            key(informationRow) {
                InformationSelectRadioGroup(
                    informationRow = informationRow,
                    selectInformation = { selectInformation(rowIndex, it) },
                )
            }
        }
    }
}

@Composable
internal fun InformationSelectRadioGroup(
    informationRow: ImmutableList<InformationRadioGroupUiModel>,
    selectInformation: (InformationRadioGroupUiModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    val (first, last) = informationRow

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(NWKTheme.spacing.space300),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        InformationSelectRadioButton(
            modifier = Modifier.weight(1f),
            text = first.text,
            isSelected = first.isSelected,
            onClick = {
                selectInformation(first)
            },
        )
        Text(
            text = "vs",
            style = NWKTheme.typography.body1.copy(
                fontWeight = FontWeight.W500,
                color = NWKTheme.color.Semantic.Text.body,
            ),
        )
        InformationSelectRadioButton(
            modifier = Modifier.weight(1f),
            text = last.text,
            isSelected = last.isSelected,
            onClick = { selectInformation(last) },
        )
    }
}

@Composable
internal fun InformationSelectRadioButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    colors: InformationSelectRadioButtonColors = InformationSelectRadioButtonDefaults.colors(),
) {
    val contentColor: Color by colors.contentColor(isSelected)
    val borderStroke: BorderStroke by InformationSelectRadioButtonDefaults.borderStroke(isSelected)

    Surface(
        modifier = modifier
            .semantics { role = Role.RadioButton }
            .clip(NWKTheme.radius.borderRadius400)
            .clickable(
                enabled = true,
                onClick = onClick,
            ),
        shape = InformationSelectRadioButtonDefaults.shape,
        color = InformationSelectRadioButtonDefaults.backgroundColor,
        contentColor = contentColor,
        border = borderStroke,
    ) {
        Text(
            modifier = Modifier
                .wrapContentWidth()
                .padding(InformationSelectRadioButtonDefaults.contentPadding),
            text = text,
            style = NWKTheme.typography.subTitle1.copy(
                color = contentColor,
            ),
            maxLines = 1,
            textAlign = TextAlign.Center,
        )
    }
}

@Preview
@Composable
private fun InformationSelectRadioGroupPreview(
    @PreviewParameter(PreviewInformationSelectRadioGroupProvider::class) informationRow: ImmutableList<InformationRadioGroupUiModel>,
) {
    NWKTheme {
        Box(modifier = Modifier.background(NWKTheme.color.Neutral.white)) {
            InformationSelectRadioGroup(
                informationRow = informationRow,
                selectInformation = {},
            )
        }
    }
}

@Preview
@Composable
private fun InformationSelectRadioButtonPreview(
    @PreviewParameter(PreviewInformationSelectRadioButtonProvider::class) information: InformationRadioGroupUiModel,
) {
    NWKTheme {
        InformationSelectRadioButton(
            text = information.text,
            isSelected = information.isSelected,
            onClick = {},
        )
    }
}
