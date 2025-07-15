package team.noweekend.feature.calendar.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.feature.calendar.mvi.CalendarViewModel
import team.noweekend.feature.calendar.mvi.builder.rememberIntentBuilder
import team.noweekend.feature.calendar.mvi.rememberSideEffectHandler

@Composable
internal fun CalendarRoute(
    modifier: Modifier = Modifier,
    calendarViewModel: CalendarViewModel = hiltViewModel(),
) {


    val intentBuilder = rememberIntentBuilder { calendarIntent ->
        calendarViewModel.intent(calendarIntent)
    }

    val state = calendarViewModel.uiState.collectAsStateWithLifecycle()

    val calendarSideEffectHandler = rememberSideEffectHandler(
        calendarPagerState = state.value.calendarPagerState,
        scrollToInitialWeekPage = state.value.calendarPagerState::scrollToInitialWeekPage,
        scrollToMonthPage = state.value.calendarPagerState::scrollToMonthPage,
        collectMonthPagerData = intentBuilder::updateMonthCalendarAndChooser,
        collectWeekPagerData = intentBuilder::updateWeekCalendarAndChooser,
        updateNextWeekPage = intentBuilder::updateNextWeekPage,
        updateNextMonthPage = intentBuilder::updateNextMonthPage,
        updatePreviousMonthPage = intentBuilder::updatePreviousMonthPage,
        updatePreviousWeekPage = intentBuilder::updatePreviousWeekPage,
    )



    LaunchedEffect(Unit) {
        with(intentBuilder) {
            collectCalendarEvent()
            updateCalendarData()
        }
        calendarViewModel.sideEffect.collect(calendarSideEffectHandler::handleSideEffect)
    }
    LaunchedEffect(state.value.calendarMode) {
        with(intentBuilder) {
            initCalendarData(state.value.calendarPagerState.initialPage)
        }
    }
    LaunchedEffect(state.value.calendarState.selectedDate) {
        intentBuilder.updateTodoList(targetDate = state.value.calendarState.selectedDate)
    }


    CalendarScreen(
        modifier = modifier,
        calendarUiState = state,
        onToggleStateChanged = intentBuilder::updateCalendarModeWithToggleState,
        onClickToggle = intentBuilder::updateCalendarMode,
        onClickDateOfWeek = intentBuilder::updateTargetDate,
        onClickYearMonthButton = {},
        onClickCheckBox = {},
        onClickOptionButton = {},
        todoList = state.value.selectedTodoList,
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
