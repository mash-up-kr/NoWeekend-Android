package team.noweekend.feature.home.component.recommend.monthly

import NWKCalender
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.ImmutableMap
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.persistentMapOf
import kotlinx.datetime.LocalDate
import team.noweekend.core.common.kotlin.extension.now
import team.noweekend.core.common.ui.calendar.model.CalendarState
import team.noweekend.core.common.ui.calendar.model.CalendarWeeksData
import team.noweekend.core.common.ui.calendar.state.CalendarPagerState
import team.noweekend.core.common.ui.calendar.state.rememberCalendarPagerState
import team.noweekend.core.design.system.core.component.divider.NWKHorizontalDivider
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.feature.home.component.recommend.monthly.card.MonthlyVacationCard
import team.noweekend.feature.home.component.recommend.monthly.header.MonthlyVacationRecommendHeader
import team.noweekend.feature.home.model.MonthlyVacationRecommendUiModel

internal fun LazyListScope.monthlyVacationRecommendComponent(
    currentMonthWeek: LocalDate,
    currentLocation: String,
    recommends: ImmutableList<MonthlyVacationRecommendUiModel>,
    calendarData: ImmutableMap<Int, CalendarWeeksData>,
    selectedDate: LocalDate,
    modifier: Modifier = Modifier,
) = item {
    MonthlyVacationRecommendComponent(
        currentMonthWeek = currentMonthWeek,
        currentLocation = currentLocation,
        recommends = recommends,
        calendarData = calendarData,
        selectedDate = selectedDate,
    )
}

@Composable
internal fun MonthlyVacationRecommendComponent(
    currentMonthWeek: LocalDate,
    currentLocation: String,
    recommends: ImmutableList<MonthlyVacationRecommendUiModel>,
    calendarData: ImmutableMap<Int, CalendarWeeksData>,
    selectedDate: LocalDate,
    modifier: Modifier = Modifier,
    calendarPagerState: CalendarPagerState = rememberCalendarPagerState(),
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(NWKTheme.color.Neutral.white),
    ) {
        MonthlyVacationRecommendHeader(
            currentMonthWeek = currentMonthWeek,
            currentLocation = currentLocation,
        )
        NWKCalender(
            calendarState = CalendarState.Week(
                pagerData = calendarData,
                pagerState = calendarPagerState.weekPagerState,
                selectedDate = selectedDate,
                calendarItemClickable = false,
            ),
            onClickDateOfWeek = {},
            userScrollEnabled = false,
        )
        Spacer(modifier = Modifier.size(16.dp))
        recommends.forEachIndexed { index, content ->
            val isLastItem = index == recommends.lastIndex
            MonthlyVacationCard(
                date = content.localDate,
                recommendContent = content.recommendContent,
                onClickRecommendedVacation = {},
            )
            if (isLastItem.not()) {
                NWKHorizontalDivider(thickness = 1.dp)
            }
        }
    }
}

@Preview
@Composable
private fun MonthlyVacationRecommendComponentPreview() {
    NWKTheme {
        MonthlyVacationRecommendComponent(
            currentMonthWeek = LocalDate.now(),
            currentLocation = "서울특별시 용산구 동자동",
            recommends = persistentListOf(
                MonthlyVacationRecommendUiModel(
                    localDate = LocalDate.now(),
                    recommendContent = "오후에 비 와요, 연차 어때요?",
                ),
                MonthlyVacationRecommendUiModel(
                    localDate = LocalDate.now(),
                    recommendContent = "오전 눈 예보, 반차 추천!",
                ),
                MonthlyVacationRecommendUiModel(
                    localDate = LocalDate.now(),
                    recommendContent = "오후에 비 와요, 연차 어때요?",
                ),
                MonthlyVacationRecommendUiModel(
                    localDate = LocalDate.now(),
                    recommendContent = "오전 눈 예보, 반차 추천!",
                ),
            ),
            calendarData = persistentMapOf(),
            selectedDate = LocalDate.now(),
        )
    }
}
