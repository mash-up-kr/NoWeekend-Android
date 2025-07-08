package team.noweekend.feature.create.vacation.recommend.component.result

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.noweekend.core.design.system.core.component.icon.NWKIcon
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.core.resource.NWKDrawableResource

@Composable
internal fun RecommendDateText(
    recommendedDate: String,
    onRecommendDateClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .clip(
                shape = NWKTheme.radius.borderRadius400,
            )
            .clickable(onClick = { onRecommendDateClick(recommendedDate) })
            .border(
                width = 1.dp,
                color = NWKTheme.color.Toast.toast500,
                shape = NWKTheme.radius.borderRadius400,
            )
            .background(
                color = NWKTheme.color.Neutral.white,
                shape = NWKTheme.radius.borderRadius400,
            )
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(NWKTheme.spacing.space300),
    ) {
        Text(
            modifier = Modifier,
            text = recommendedDate,
            style = NWKTheme.typography.heading6.copy(
                fontWeight = FontWeight.W700,
                color = NWKTheme.color.Semantic.Text.neutral,
            ),
        )
        NWKIcon(
            modifier = Modifier.size(24.dp),
            resourceId = NWKDrawableResource.Plus,
            tint = NWKTheme.color.Semantic.Text.body,
        )
    }
}

@Preview
@Composable
private fun SpeechBubblePreview() {
    NWKTheme {
        RecommendDateText(
            recommendedDate = "바보 텍스트",
            onRecommendDateClick = {},
        )
    }
}
