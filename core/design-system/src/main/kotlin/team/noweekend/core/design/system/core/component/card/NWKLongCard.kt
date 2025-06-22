package team.noweekend.core.design.system.core.component.card

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.core.resource.NWKDrawableResource

@Composable
fun NWKLongCard(
    title: String,
    description: String,
    leadingDrawableResId: Int,
    trailingDrawableResId: Int,
    onCardClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
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
        content = {
            NWKLongCardContent(
                title = title,
                description = description,
                leadingDrawableResId = leadingDrawableResId,
                trailingDrawableResId = trailingDrawableResId,
            )
        },
    )
}

@Composable
private fun RowScope.NWKLongCardContent(
    title: String,
    description: String,
    @DrawableRes leadingDrawableResId: Int,
    @DrawableRes trailingDrawableResId: Int,
    modifier: Modifier = Modifier,
) {
    // TODO(JaesungLeee) : NWKImage 대체
    Image(
        modifier = Modifier.size(62.dp),
        painter = painterResource(id = leadingDrawableResId),
        contentDescription = null,
    )
    Column(
        modifier = Modifier.weight(1f),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = title,
            style = NWKTheme.typography.subTitle1.copy(
                color = NWKTheme.color.Semantic.Text.body,
            ),
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = description,
            style = NWKTheme.typography.heading6.copy(
                color = NWKTheme.color.Semantic.Text.neutral,
            ),
        )
    }
    // TODO(JaesungLeee) : NWKIcon 대체
    Icon(
        modifier = Modifier.size(24.dp),
        painter = painterResource(trailingDrawableResId),
        tint = NWKTheme.color.Neutral.neutralGray700,
        contentDescription = null,
    )
}

@Preview
@Composable
private fun LongCardPreview() {
    NWKTheme {
        NWKLongCard(
            title = "Max 1line",
            description = "0/00(월) ~ 0/00(월)",
            leadingDrawableResId = NWKDrawableResource.Cake,
            trailingDrawableResId = NWKDrawableResource.Plus,
            onCardClick = {},
        )
    }
}
