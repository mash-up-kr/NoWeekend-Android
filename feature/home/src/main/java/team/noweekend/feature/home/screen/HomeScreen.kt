package team.noweekend.feature.home.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import team.noweekend.core.common.android.extension.fillMaxWidthOfScreen
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.feature.home.component.holiday.holidayRecommend
import team.noweekend.feature.home.component.recommend.personal.personalVacationRecommend

@Composable
internal fun HomeScreen(
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(NWKTheme.color.Neutral.white),
    ) {
        item {
            Text(
                modifier = Modifier
                    .fillMaxWidthOfScreen()
                    .padding(
                        top = NWKTheme.spacing.space175,
                        start = NWKTheme.spacing.space300,
                        end = NWKTheme.spacing.space300,
                        bottom = NWKTheme.spacing.space100,
                    ),
                text = "오늘 연차쓸래?",
                style = NWKTheme.typography.heading4.copy(
                    color = NWKTheme.color.Semantic.Text.neutral,
                ),
            )
        }
        personalVacationRecommend(
            vacationDays = 5,
            userName = "자성리",
            onCardClick = {},
            onFilterClick = {},
        )
        holidayRecommend(
            onHolidayCardClick = {},
        )
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    NWKTheme {
        HomeScreen()
    }
}
