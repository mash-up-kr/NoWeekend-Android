package team.noweekend.feature.home.component.popular.carousel

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.buildAnnotatedString
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import team.noweekend.core.common.android.extension.fillMaxWidthOfScreen
import team.noweekend.core.design.system.core.component.card.NWKShortCard
import team.noweekend.core.design.system.core.component.control.page.NWKPageControl
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.core.resource.NWKDrawableResource
import team.noweekend.feature.home.component.common.carousel.CarouselLayout

@Composable
internal fun PopularVacationCarousel(
    modifier: Modifier = Modifier,
) {
    val carouselState = rememberPagerState(pageCount = { 4 })
    val item = persistentListOf("A", "B", "C", "D")
    Column(
        modifier = modifier.fillMaxWidthOfScreen(),
        verticalArrangement = Arrangement.spacedBy(NWKTheme.spacing.space175),
    ) {
        CarouselLayout(
            state = carouselState,
            pageWidth = 335,
            pageSpacing = NWKTheme.spacing.space200,
            contentPadding = PaddingValues(horizontal = NWKTheme.spacing.space200),
            content = { index ->
                PopularVacationCarouselGridLayout(
                    items = item
                ) {
                    NWKShortCard(
                        date = item[it],
                        drawableResId = NWKDrawableResource.Cake,
                        description = buildAnnotatedString {  },
                        onCardClick = {},
                    )
                }
            }
        )
        NWKPageControl(
            pageSize = { 1 },
            currentPosition = { 0 }
        )
    }
}

@Composable
internal fun PopularVacationCarouselGridLayout(
    items: ImmutableList<String>,
    modifier: Modifier = Modifier,
    columns: Int = 2,
    content: @Composable (Int) -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(NWKTheme.spacing.space175)
    ) {
        val rows = (items.size + columns - 1) / columns

        for (rowIndex in 0 until rows) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(NWKTheme.spacing.space175)
            ) {
                for (columnIndex in 0 until columns) {
                    val itemIndex = rowIndex * columns + columnIndex
                    Box(
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        if (itemIndex < items.size) {
                            content(itemIndex)
                        }
                    }
                }
            }
        }
    }
}
