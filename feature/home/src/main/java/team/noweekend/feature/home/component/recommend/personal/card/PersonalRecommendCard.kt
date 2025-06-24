package team.noweekend.feature.home.component.recommend.personal.card

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import team.noweekend.core.design.system.core.component.card.NWKLongCard
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.core.resource.NWKDrawableResource

@Composable
internal fun PersonalRecommendCard(
    title: String,
    date: String,
    @DrawableRes leadingDrawableResId: Int,
    onCardClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    @DrawableRes trailingDrawableResId: Int = NWKDrawableResource.Plus,
) {
    NWKLongCard(
        title = title,
        description = date,
        leadingDrawableResId = leadingDrawableResId,
        trailingDrawableResId = trailingDrawableResId,
        onCardClick = onCardClick,
        modifier = modifier,
        enabled = enabled,
    )
}

@Preview
@Composable
private fun PersonalRecommendCardPreview() {
    NWKTheme {
        PersonalRecommendCard(
            title = "Max 1line",
            date = "0/00(월) ~ 0/00(월)",
            leadingDrawableResId = NWKDrawableResource.Cake,
            trailingDrawableResId = NWKDrawableResource.Plus,
            onCardClick = {},
        )
    }
}
