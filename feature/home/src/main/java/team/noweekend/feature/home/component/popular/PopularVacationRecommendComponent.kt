package team.noweekend.feature.home.component.popular

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.ImmutableMap
import team.noweekend.core.common.android.extension.fillMaxWidthOfScreen
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.feature.home.component.popular.carousel.PopularVacationCarousel
import team.noweekend.feature.home.model.PopularVacationUiModel

@Composable
internal fun PopularVacationRecommendComponent(
    popularVacations: ImmutableMap<Int, ImmutableList<PopularVacationUiModel>>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidthOfScreen(),
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
private fun ColumnScope.PopularVacationRecommendContent(
    popularVacations: ImmutableMap<Int, ImmutableList<PopularVacationUiModel>>,
    modifier: Modifier = Modifier,
) {
    PopularVacationCarousel(
        popularVacations = popularVacations,
    )
}
