package team.noweekend.feature.calendar.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import team.noweekend.core.common.ui.calendar.model.CalendarMode
import team.noweekend.core.common.ui.calendar.model.CalendarState
import team.noweekend.core.common.ui.calendar.state.rememberCalendarPagerState
import team.noweekend.core.common.ui.todo.model.Todo
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.feature.calendar.mvi.CalendarViewModel
import team.noweekend.feature.calendar.mvi.builder.rememberIntentBuilder
import team.noweekend.feature.calendar.mvi.rememberSideEffectHandler

@Composable
internal fun CalendarRoute(
    modifier: Modifier = Modifier,
    calendarViewModel: CalendarViewModel = hiltViewModel(),
) {

    val calendarPagerState = rememberCalendarPagerState()

    val intentBuilder = rememberIntentBuilder { calendarIntent ->
        calendarViewModel.intent(calendarIntent)
    }

    val state = calendarViewModel.uiState.collectAsStateWithLifecycle()
    val chooserMonth = remember {
        derivedStateOf {
            state.value.chooserMonth
        }
    }

    val calendarSideEffectHandler = rememberSideEffectHandler(
        calendarPagerState = calendarPagerState,
        scrollToInitialWeekPage = calendarPagerState::scrollToInitialWeekPage,
        scrollToMonthPage = calendarPagerState::scrollToMonthPage,
        collectMonthPagerData = intentBuilder::updateMonthCalendarAndChooser,
        collectWeekPagerData = intentBuilder::updateWeekCalendarAndChooser,
        updateNextWeekPage = intentBuilder::updateNextWeekPage,
        updateNextMonthPage = intentBuilder::updateNextMonthPage,
        updatePreviousMonthPage = intentBuilder::updatePreviousMonthPage,
        updatePreviousWeekPage = intentBuilder::updatePreviousWeekPage,
    )

    val calendarState: State<CalendarState> = remember {
        derivedStateOf {
            when (state.value.calendarMode) {
                CalendarMode.WEEK -> {
                    CalendarState.Week(
                        mode = state.value.calendarMode,
                        selectedDate = state.value.selectedDate,
                        pagerState = calendarPagerState.weekPagerState,
                        pagerData = state.value.calendarWeeksData,
                    )
                }

                CalendarMode.MONTH -> {
                    CalendarState.Month(
                        mode = state.value.calendarMode,
                        selectedDate = state.value.selectedDate,
                        pagerState = calendarPagerState.monthPagerState,
                        pagerData = state.value.calendarMonthsData,
                    )
                }
            }
        }
    }


    LaunchedEffect(Unit) {
        with(intentBuilder) {
            collectCalendarEvent()
            updateCalendarData()
        }
        calendarViewModel.sideEffect.collect(calendarSideEffectHandler::handleSideEffect)
    }
    LaunchedEffect(state.value.calendarMode) {
        intentBuilder.initCalendarData(calendarPagerState.initialPage)
    }

    CalendarScreen(
        modifier = modifier,
        calendarState = calendarState.value,
        chooserDate = chooserMonth,
        onToggleStateChanged = intentBuilder::updateCalendarModeWithToggleState,
        onClickToggle = intentBuilder::updateCalendarMode,
        onClickDateOfWeek = intentBuilder::updateTargetDate,
        onClickYearMonthButton = {},
        onClickCheckBox = {},
        onClickOptionButton = {},
        todoList = Todo.dummy,
    )
}

@Preview
@Composable
private fun CalendarRoutePreview() {
    NWKTheme {
        CalendarRoute(
            modifier = Modifier.fillMaxSize(),
        )
    }
}
