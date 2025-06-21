package team.noweekend.feature.home.component.recommend.personal.carousel

import android.content.res.Resources
import androidx.annotation.Dimension
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import team.noweekend.core.common.android.util.getScreenWidth
import team.noweekend.core.design.system.core.component.control.page.NWKPageControl
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.core.resource.NWKDrawableResource
import team.noweekend.feature.home.component.recommend.personal.card.PersonalRecommendCard

fun @receiver:Dimension(unit = Dimension.PX) Int.toDp(): Dp =
    (this / Resources.getSystem().displayMetrics.density).toInt().dp

@Composable
internal fun PersonalRecommendCarousel(
    onCardClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val pagerState = rememberPagerState(pageCount = { 5 })
    val pageSize = (getScreenWidth() * 0.79f).toInt().toDp()

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(NWKTheme.spacing.space175),
    ) {
        HorizontalPager(
            state = pagerState,
            pageSpacing = NWKTheme.spacing.space175,
            contentPadding = PaddingValues(horizontal = NWKTheme.spacing.space200),
            pageSize = PageSize.Fixed(pageSize = pageSize),
        ) { _ ->
            PersonalRecommendCard(
                onCardClick = onCardClick,
                imageResId = NWKDrawableResource.Cake,
            )
        }
        NWKPageControl(
            pageSize = pagerState.pageCount,
            currentPosition = pagerState.currentPage,
        )
    }
}
