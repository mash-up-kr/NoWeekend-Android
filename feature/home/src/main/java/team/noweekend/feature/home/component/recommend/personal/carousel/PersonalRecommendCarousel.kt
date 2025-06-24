package team.noweekend.feature.home.component.recommend.personal.carousel

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import team.noweekend.core.common.android.util.getScreenWidth
import team.noweekend.core.common.android.util.toDp
import team.noweekend.core.design.system.core.component.control.page.NWKPageControl
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.core.resource.NWKDrawableResource
import team.noweekend.feature.home.component.recommend.personal.card.PersonalRecommendCard

@Composable
internal fun PersonalRecommendCarousel(
    onCardClick: () -> Unit,
    modifier: Modifier = Modifier,
    // TODO (JaesungLeee) : API 연동 시 파라미터 수정 필요
    recommendVacations: ImmutableList<String> = persistentListOf("", "", "", "", ""),
) {
    val pagerState = rememberPagerState(pageCount = { recommendVacations.size })
    val pageSize = (getScreenWidth() * 0.79f).toInt().toDp() // 350/250

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(NWKTheme.spacing.space175),
    ) {
        HorizontalPager(
            state = pagerState,
            pageSpacing = NWKTheme.spacing.space175,
            contentPadding = PaddingValues(horizontal = NWKTheme.spacing.space200),
            pageSize = PageSize.Fixed(pageSize = pageSize),
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
