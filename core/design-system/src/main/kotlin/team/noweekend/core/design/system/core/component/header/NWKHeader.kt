package team.noweekend.core.design.system.core.component.header

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.noweekend.core.design.system.core.component.header.defaults.NWKHeaderDefaults
import team.noweekend.core.design.system.core.component.icon.NWKIcon
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.core.resource.NWKDrawableResource

@Composable
fun NWKHeader(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String? = null,
    trailingContent: @Composable (() -> Unit)? = null,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(NWKTheme.color.Neutral.white)
            .padding(NWKHeaderDefaults.getContentPadding()),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        NWKIcon(
            modifier = Modifier
                .size(24.dp)
                .clickable(onClick = onBackClick),
            resourceId = NWKDrawableResource.ChevronLeft,
            tint = NWKTheme.color.Semantic.Text.body,
        )
        text?.let {
            Text(
                modifier = Modifier.weight(1f),
                text = it,
                style = NWKTheme.typography.heading6.copy(
                    fontWeight = FontWeight.W700,
                    color = NWKTheme.color.Semantic.Text.neutral,
                ),
                textAlign = TextAlign.Center,
            )
        }
        trailingContent?.let {
            trailingContent.invoke()
        } ?: Spacer(modifier = Modifier.size(NWKTheme.spacing.space300))
    }
}

@Preview
@Composable
private fun NWKHeaderPreview() {
    NWKTheme {
        NWKHeader(
            text = "1/3",
            trailingContent = {
                Text(
                    text = "저장",
                    style = NWKTheme.typography.heading6.copy(
                        fontWeight = FontWeight.W700,
                        color = NWKTheme.color.Toast.toast500,
                    ),
                )
            },
            onBackClick = {},
        )
    }
}
