package team.noweekend.feature.home.component.holiday

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import team.noweekend.core.common.android.extension.fillMaxWidthOfScreen
import team.noweekend.core.design.system.core.component.scaffold.NWKScaffold
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.core.resource.NWKStringResource
import team.noweekend.feature.home.component.holiday.carousel.HolidayRecommendCarousel

internal fun LazyListScope.holidayRecommend(
    onHolidayCardClick: () -> Unit,
) = item {
    HolidayRecommendComponent(
        onHolidayCardClick = onHolidayCardClick,
    )
}

@Composable
private fun HolidayRecommendComponent(
    onHolidayCardClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidthOfScreen(),
    ) {
        HolidayRecommendHeader()
        HolidayRecommendCarousel(
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
