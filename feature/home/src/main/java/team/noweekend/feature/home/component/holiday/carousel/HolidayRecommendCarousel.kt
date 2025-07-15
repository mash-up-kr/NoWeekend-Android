package team.noweekend.feature.home.component.holiday.carousel

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.collections.immutable.ImmutableList
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.feature.home.component.common.carousel.CarouselLayout
import team.noweekend.feature.home.component.holiday.card.HolidayCard
import team.noweekend.feature.home.model.HolidayUiModel

@Composable
internal fun HolidayRecommendCarousel(
    holidays: ImmutableList<HolidayUiModel>,
    onCardClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val pagerState = rememberPagerState(pageCount = { holidays.size })

    CarouselLayout(
        modifier = modifier,
        state = pagerState,
        pageWidth = 160,
        pageSpacing = NWKTheme.spacing.space150,
        contentPadding = PaddingValues(horizontal = NWKTheme.spacing.space200),
        content = { index ->
            val holiday = holidays.getOrNull(index) ?: return@CarouselLayout
            HolidayCard(
                onCardClick = onCardClick,
                date = holiday.date,
                holiday = holiday.holiday,
            )
        },
    )
}
