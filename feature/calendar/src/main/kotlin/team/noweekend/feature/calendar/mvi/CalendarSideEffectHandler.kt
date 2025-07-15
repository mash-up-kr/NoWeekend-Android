package team.noweekend.feature.calendar.mvi

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import team.noweekend.core.common.android.mvi.SideEffectHandler
import team.noweekend.core.common.ui.calendar.state.CalendarPagerState
import team.noweekend.core.common.ui.calendar.state.rememberCalendarPagerState

@Composable
fun rememberSideEffectHandler(
    scrollToInitialWeekPage: suspend () -> Unit,
    scrollToMonthPage: suspend () -> Unit,
    collectMonthPagerData: (Int) -> Unit,
    collectWeekPagerData: (Int) -> Unit,
    updatePreviousWeekPage: (page: Int) -> Unit,
    updateNextWeekPage: (page: Int) -> Unit,
    updatePreviousMonthPage: (page: Int) -> Unit,
    updateNextMonthPage: (page: Int) -> Unit,
    navigateToDetailDate : (date: String) -> Unit,
    calendarPagerState: CalendarPagerState = rememberCalendarPagerState(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
) = remember(calendarPagerState, coroutineScope) {

    CalendarSideEffectHandler(
        scrollToInitialWeekPage = scrollToInitialWeekPage,
        scrollToMonthPage = scrollToMonthPage,
        collectWeekPagerData = collectWeekPagerData,
        collectMonthPagerData = collectMonthPagerData,
        updateNextMonthPage = updateNextMonthPage,
        updatePreviousMonthPage = updatePreviousMonthPage,
        updateNextWeekPage = updateNextWeekPage,
        updatePreviousWeekPage = updatePreviousWeekPage,
        navigateToDetailDate = navigateToDetailDate,
        calendarPagerState = calendarPagerState,
        coroutineScope = coroutineScope,
    )
}

@Stable
class CalendarSideEffectHandler(
    private val scrollToInitialWeekPage: suspend () -> Unit,
    private val scrollToMonthPage: suspend () -> Unit,
    private val collectMonthPagerData: (Int) -> Unit,
    private val collectWeekPagerData: (Int) -> Unit,
    private val updatePreviousWeekPage: (page: Int) -> Unit,
    private val updateNextWeekPage: (page: Int) -> Unit,
    private val updatePreviousMonthPage: (page: Int) -> Unit,
    private val updateNextMonthPage: (page: Int) -> Unit,
    private val navigateToDetailDate : (String) -> Unit,
    private val calendarPagerState: CalendarPagerState,
    private val coroutineScope: CoroutineScope,
) : SideEffectHandler<CalendarSideEffect> {

    override fun handleSideEffect(sideEffect: CalendarSideEffect) {
        when (sideEffect) {
            is CalendarSideEffect.CompleteInitWeekCalendar -> {
                coroutineScope.launch {
                    scrollToInitialWeekPage()
                }
            }

            is CalendarSideEffect.CompleteInitMonthCalendar -> {
                coroutineScope.launch {
                    scrollToMonthPage()
                }
            }

            is CalendarSideEffect.CollectMonthPagerStatePage -> {
                coroutineScope.launch {
                    snapshotFlow { calendarPagerState.monthPagerState.currentPage }.collect { page ->
                        collectMonthPagerData(page)
                    }
                }
            }

            is CalendarSideEffect.CollectWeekPagerStatePage -> {
                coroutineScope.launch {
                    snapshotFlow { calendarPagerState.weekPagerState.currentPage }.collect { page ->
                        collectWeekPagerData(page)
                    }
                }
            }

            is CalendarSideEffect.UpdateWeekCalendarPage -> {
                calendarPagerState.updateWeekCalendar(
                    currentPage = sideEffect.currentPage,
                    updatePreviousWeekPage = updatePreviousWeekPage,
                    updateNextWeekPage = updateNextWeekPage,
                )

            }

            is CalendarSideEffect.UpdateMonthCalendarPage -> {
                calendarPagerState.updateMonthCalendar(
                    currentPage = sideEffect.currentPage,
                    updateNextMonthPage = updateNextMonthPage,
                    updatePreviousMonthPage = updatePreviousMonthPage,
                )
            }

            is CalendarSideEffect.NavigateToDetailDate->{
                navigateToDetailDate(sideEffect.date)
            }
        }
    }
}
