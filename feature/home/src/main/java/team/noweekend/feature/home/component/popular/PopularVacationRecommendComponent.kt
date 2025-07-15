package team.noweekend.feature.home.component.popular

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import team.noweekend.core.common.android.extension.fillMaxWidthOfScreen
import team.noweekend.core.design.system.core.component.card.NWKShortCard
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.feature.home.model.PopularVacationUiModel
import team.noweekend.feature.home.model.PopularVacationUiModel.Companion.getStyledDescription

internal fun LazyListScope.popularVacationRecommend(
    popularVacations: ImmutableList<PopularVacationUiModel>,
    modifier: Modifier = Modifier,
) = item {
    PopularVacationRecommendComponent(
        popularVacations = popularVacations,
        modifier = modifier,
    )
}

@Composable
internal fun PopularVacationRecommendComponent(
    popularVacations: ImmutableList<PopularVacationUiModel>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidthOfScreen(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        PopularVacationRecommendHeader()
        PopularVacationRecommendContent(
            popularVacations = popularVacations,
        )
    }
}

@Composable
private fun PopularVacationRecommendHeader(
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                start = NWKTheme.spacing.space300,
                end = NWKTheme.spacing.space300,
                bottom = NWKTheme.spacing.space200,
            ),
        text = "인기 휴가, 모두 확인하세요",
        style = NWKTheme.typography.heading5.copy(
            color = NWKTheme.color.Semantic.Text.neutral,
        ),
    )
}

@Composable
private fun PopularVacationRecommendContent(
    popularVacations: ImmutableList<PopularVacationUiModel>,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        modifier = modifier
            .fillMaxWidth()
            .height(500.dp)
            .padding(horizontal = 20.dp),
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        content = {
            items(popularVacations) {
                NWKShortCard(
                    date = it.displayDate,
                    drawableResId = it.imageResourceId,
                    description = it.vacationType.getStyledDescription(),
                    onCardClick = {},
                )
            }
        },
    )
}
