package team.noweekend.feature.create.vacation.information.component.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.feature.create.vacation.information.component.button.defaults.InformationSelectRadioButtonColors
import team.noweekend.feature.create.vacation.information.component.button.defaults.InformationSelectRadioButtonDefaults
import team.noweekend.feature.create.vacation.information.component.button.preview.PreviewInformationSelectRadioButtonProvider
import team.noweekend.feature.create.vacation.information.model.SelectedInformationUiModel

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
                .widthIn(min = 108.dp)
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
private fun InformationSelectRadioButtonPreview(
    @PreviewParameter(PreviewInformationSelectRadioButtonProvider::class) information: SelectedInformationUiModel,
) {
    NWKTheme {
        InformationSelectRadioButton(
            text = information.text,
            isSelected = information.isSelected,
            onClick = {},
        )
    }
}
