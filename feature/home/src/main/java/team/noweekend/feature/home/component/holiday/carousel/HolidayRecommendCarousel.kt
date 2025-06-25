package team.noweekend.feature.home.component.holiday.carousel

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.datetime.LocalDate
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.feature.home.component.common.carousel.CarouselLayout
import team.noweekend.feature.home.component.holiday.card.HolidayCard

@Composable
internal fun HolidayRecommendCarousel(
    onCardClick: () -> Unit,
    modifier: Modifier = Modifier,
    // TODO (JaesungLeee) : API 연동 시 파라미터 수정 필요
    holidays: ImmutableList<String> = persistentListOf("", "", "", "", ""),
) {
    val pagerState = rememberPagerState(pageCount = { holidays.size })

    CarouselLayout(
        modifier = modifier,
        state = pagerState,
        pageWidth = 160,
        pageSpacing = NWKTheme.spacing.space150,
        contentPadding = PaddingValues(horizontal = NWKTheme.spacing.space200),
        content = { index ->
            HolidayCard(
                onCardClick = onCardClick,
                date = LocalDate(2025, 6, 6),
                holiday = "현충일",
            )
        },
    )
}
