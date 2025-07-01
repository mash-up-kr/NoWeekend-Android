package team.noweekend.core.design.system.core.component.card

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.noweekend.core.design.system.core.component.icon.NWKIcon
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.core.resource.NWKDrawableResource

@Composable
fun NWKHolidayCard(
    title: String,
    description: String,
    onCardClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    Column(
        modifier = modifier
            .width(160.dp)
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
                color = NWKTheme.color.Semantic.Background.alternative01,
                shape = NWKTheme.radius.borderRadius500,
            )
            .padding(
                horizontal = NWKTheme.spacing.space175,
                vertical = NWKTheme.spacing.space300,
            ),
        horizontalAlignment = Alignment.Start,
        content = {
            NWKHolidayCardContent(
                title = title,
                description = description,
                modifier = Modifier,
            )
        },
    )
}

@Composable
private fun ColumnScope.NWKHolidayCardContent(
    title: String,
    description: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = title,
            style = NWKTheme.typography.subTitle1.copy(
                color = NWKTheme.color.Semantic.Text.body,
            ),
        )
        NWKIcon(
            resourceId = NWKDrawableResource.Plus,
            modifier = Modifier.size(24.dp),
            tint = NWKTheme.color.Neutral.neutralGray700,
        )
    }
    Spacer(modifier = Modifier.size(2.dp))
    Text(
        text = description,
        style = NWKTheme.typography.heading6.copy(
            color = NWKTheme.color.Semantic.Text.body,
        ),
    )
}

@Preview
@Composable
private fun NWKHolidayCardPreview() {
    NWKTheme {
        NWKHolidayCard(
            title = "6/06(일)",
            description = "현충일",
            onCardClick = {},
        )
    }
}
