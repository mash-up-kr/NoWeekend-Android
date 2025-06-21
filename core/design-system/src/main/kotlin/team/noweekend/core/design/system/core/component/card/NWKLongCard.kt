package team.noweekend.core.design.system.core.component.card

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.noweekend.core.design.system.foundation.theme.NWKTheme

@Composable
fun NWKLongCard(
    onCardClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit,
) {
    Row(
        modifier = modifier
            .border(
                width = 1.dp,
                color = NWKTheme.color.Semantic.Border.border01,
                shape = NWKTheme.radius.borderRadius500,
            )
            .clip(NWKTheme.radius.borderRadius500)
            .clickable(
                enabled = enabled,
                onClick = onCardClick,
            )
            .background(
                color = NWKTheme.color.Semantic.Background.normal,
                shape = NWKTheme.radius.borderRadius500,
            )
            .padding(
                horizontal = NWKTheme.spacing.space175,
                vertical = NWKTheme.spacing.space300,
            ),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        content = content,
    )
}

@Preview
@Composable
private fun LongCardPreview() {
    NWKTheme {
        NWKLongCard(
            onCardClick = {},
            modifier = Modifier.fillMaxWidth(),
            content = {},
        )
    }
}
