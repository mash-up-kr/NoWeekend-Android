package team.noweekend.core.design.system.core.component.input.atomics

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import team.noweekend.core.design.system.foundation.theme.NWKTheme

@Composable
internal fun NWKTextFieldLabel(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        text = text,
        style = NWKTheme.typography.subTitle1.copy(
            fontWeight = FontWeight.W500,
            color = NWKTheme.color.Semantic.Text.body,
        ),
    )
}
