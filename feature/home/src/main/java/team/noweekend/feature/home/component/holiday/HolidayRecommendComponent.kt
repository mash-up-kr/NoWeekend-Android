package team.noweekend.feature.home.component.holiday

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.collections.immutable.ImmutableList
import team.noweekend.core.common.android.extension.fillMaxWidthOfScreen
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.core.resource.NWKStringResource
import team.noweekend.feature.home.component.holiday.carousel.HolidayRecommendCarousel
import team.noweekend.feature.home.model.HolidayUiModel

internal fun LazyListScope.holidayRecommend(
    holidays: ImmutableList<HolidayUiModel>,
    onHolidayCardClick: (HolidayUiModel) -> Unit,
) = item {
    HolidayRecommendComponent(
        holidays = holidays,
        onHolidayCardClick = { onHolidayCardClick(it) },
    )
}

@Composable
internal fun HolidayRecommendComponent(
    holidays: ImmutableList<HolidayUiModel>,
    onHolidayCardClick: (HolidayUiModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidthOfScreen()
            .background(NWKTheme.color.Neutral.neutralGray100)
            .padding(vertical = NWKTheme.spacing.space300),
    ) {
        HolidayRecommendHeader()
        HolidayRecommendCarousel(
            holidays = holidays,
            onCardClick = onHolidayCardClick,
        )
    }
}

@Composable
private fun HolidayRecommendHeader(
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                start = NWKTheme.spacing.space300,
                end = NWKTheme.spacing.space200,
                bottom = NWKTheme.spacing.space200,
            ),
        text = stringResource(NWKStringResource.HomeHolidayRecommendHeader),
        style = NWKTheme.typography.heading5.copy(
            color = NWKTheme.color.Semantic.Text.neutral,
        ),
    )
}

@Preview
@Composable
private fun HolidayRecommendComponentPreview() {
    NWKTheme {
        HolidayRecommendHeader()
    }
}
