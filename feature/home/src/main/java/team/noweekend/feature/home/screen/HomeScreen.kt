package team.noweekend.feature.home.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.persistentListOf
import kotlinx.datetime.LocalDate
import team.noweekend.core.common.android.extension.fillMaxWidthOfScreen
import team.noweekend.core.common.kotlin.extension.now
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.core.model.vacation.VacationType
import team.noweekend.feature.home.component.common.spacer.itemSpacer
import team.noweekend.feature.home.component.holiday.holidayRecommend
import team.noweekend.feature.home.component.popular.popularVacationRecommend
import team.noweekend.feature.home.component.recommend.monthly.monthlyVacationRecommendComponent
import team.noweekend.feature.home.component.vacation.createVacation
import team.noweekend.feature.home.model.PopularVacationUiModel
import team.noweekend.feature.home.mvi.HomeUiState

@Composable
internal fun HomeScreen(
    uiState: HomeUiState,
    onCreateVacationClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(NWKTheme.color.Neutral.white),
    ) {
        homeHeader()
        itemSpacer(32.dp)
        createVacation(
            temperature = 90,
            createVacationStatus = uiState.createVacationStatus,
            onCreateVacationClick = onCreateVacationClick,
        )
        itemSpacer(40.dp)
        holidayRecommend(
            holidays = uiState.remainedHolidays,
            onHolidayCardClick = {},
        )
        itemSpacer(40.dp)
        monthlyVacationRecommendComponent(
            currentMonthWeek = LocalDate.now(),
            currentLocation = "서울특별시 중구 소공동 세종대로18길 2",
            recommends = uiState.weatherRecommendVacations,
        )
        itemSpacer(40.dp)
        popularVacationRecommend(
            popularVacations = persistentListOf(
                PopularVacationUiModel(
                    vacationType = VacationType.HOLIDAY_EXIST,
                    startLocalDate = LocalDate.now(),
                    endLocalDate = LocalDate.now(),
                ),
                PopularVacationUiModel(
                    vacationType = VacationType.BIRTHDAY_EXIST,
                    startLocalDate = LocalDate.now(),
                    endLocalDate = LocalDate.now(),
                ),
                PopularVacationUiModel(
                    vacationType = VacationType.INCLUDE_MONDAY,
                    startLocalDate = LocalDate.now(),
                    endLocalDate = LocalDate.now(),
                ),
                PopularVacationUiModel(
                    vacationType = VacationType.INCLUDE_MONDAY,
                    startLocalDate = LocalDate.now(),
                    endLocalDate = LocalDate.now(),
                ),
            ),
        )
//        itemSpacer(72.dp)
    }
}

private fun LazyListScope.homeHeader(
    modifier: Modifier = Modifier,
) {
    item {
        Text(
            modifier = Modifier
                .fillMaxWidthOfScreen()
                .background(NWKTheme.color.Toast.toast50)
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
}

@Preview
@Composable
private fun HomeScreenPreview() {
    NWKTheme {
        HomeScreen(
            uiState = HomeUiState.INITIAL_STATE,
            onCreateVacationClick = {},
        )
    }
}
