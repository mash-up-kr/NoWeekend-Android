package team.noweekend.feature.home.component.recommend.personal.carousel

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import team.noweekend.core.design.system.core.component.control.page.NWKPageControl
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.core.resource.NWKDrawableResource
import team.noweekend.feature.home.component.common.carousel.CarouselLayout
import team.noweekend.feature.home.component.recommend.personal.card.PersonalRecommendCard

@Composable
internal fun PersonalRecommendCarousel(
    onCardClick: () -> Unit,
    modifier: Modifier = Modifier,
    // TODO (JaesungLeee) : API 연동 시 파라미터 수정 필요
    recommendVacations: ImmutableList<String> = persistentListOf("", "", "", "", ""),
) {
    val pagerState = rememberPagerState(pageCount = { recommendVacations.size })

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(NWKTheme.spacing.space175),
    ) {
        CarouselLayout(
            state = pagerState,
            pageWidth = 295,
            pageSpacing = NWKTheme.spacing.space175,
            contentPadding = PaddingValues(horizontal = NWKTheme.spacing.space200),
        ) { index ->
            PersonalRecommendCard(
                onCardClick = onCardClick,
                title = "title",
                date = "date",
                leadingDrawableResId = NWKDrawableResource.Cake,
                trailingDrawableResId = NWKDrawableResource.Plus,
            )
        }
        NWKPageControl(
            pageSize = { pagerState.pageCount },
            currentPosition = { pagerState.currentPage },
        )
    }
}
