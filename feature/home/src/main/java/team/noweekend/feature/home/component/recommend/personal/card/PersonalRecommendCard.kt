package team.noweekend.feature.home.component.recommend.personal.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.noweekend.core.design.system.core.component.card.NWKLongCard
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.core.resource.NWKDrawableResource

@Composable
internal fun PersonalRecommendCard(
    onCardClick: () -> Unit,
    imageResId: Int,
    modifier: Modifier = Modifier,
    iconResId: Int = NWKDrawableResource.Plus,
) {
    NWKLongCard(
        modifier = modifier,
        onCardClick = onCardClick,
        content = {
            VacationRecommendCardContent(
                imageResId = imageResId,
                iconResId = iconResId,
            )
        },
    )
}

@Composable
private fun RowScope.VacationRecommendCardContent(
    imageResId: Int,
    iconResId: Int,
    modifier: Modifier = Modifier,
) {
    Image(
        modifier = Modifier.size(62.dp),
        painter = painterResource(id = imageResId),
        contentDescription = null,
    )
    Column(
        modifier = Modifier.weight(1f),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Max 1line",
            style = NWKTheme.typography.subTitle1.copy(
                color = NWKTheme.color.Semantic.Text.body,
            ),
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "0/00(월) ~ 0/00(월)",
            style = NWKTheme.typography.heading6.copy(
                color = NWKTheme.color.Semantic.Text.neutral,
            ),
        )
    }
    Icon(
        modifier = Modifier.size(24.dp),
        painter = painterResource(iconResId),
        tint = NWKTheme.color.Neutral.neutralGray700,
        contentDescription = null,
    )
}

@Preview
@Composable
private fun PersonalRecommendCardPreview() {
    NWKTheme {
        PersonalRecommendCard(
            onCardClick = {},
            imageResId = NWKDrawableResource.Cake,
        )
    }
}
