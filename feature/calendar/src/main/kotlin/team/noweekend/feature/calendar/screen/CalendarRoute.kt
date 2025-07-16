package team.noweekend.feature.calendar.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.LifecycleResumeEffect
import androidx.lifecycle.compose.LifecycleStartEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.datetime.LocalDate
import team.noweekend.core.common.android.extension.fillMaxWidthOfScreen
import team.noweekend.core.common.ui.todo.model.Todo
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.feature.calendar.component.fab.FabLayout
import team.noweekend.feature.calendar.component.fab.core.FabZIndex
import team.noweekend.feature.calendar.mvi.CalendarViewModel
import team.noweekend.feature.calendar.mvi.builder.rememberIntentBuilder
import team.noweekend.feature.calendar.mvi.rememberSideEffectHandler

@Composable
internal fun CalendarRoute(
    navigateToDetailDate: (String) -> Unit,
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
        navigateToDetailDate = navigateToDetailDate
    )



    LaunchedEffect(Unit){
        with(intentBuilder) {
            collectCalendarEvent()
            updateCalendarData()
            calendarViewModel.sideEffect.collect(calendarSideEffectHandler::handleSideEffect)
        }
    }
    LifecycleEventEffect(Lifecycle.Event.ON_START) {
        intentBuilder.updateCalendarState()
    }
    LaunchedEffect(state.value.calendarMode) {
        with(intentBuilder) {
            initCalendarData(state.value.calendarPagerState.initialPage)
        }
    }
    LaunchedEffect(state.value.calendarState.selectedDate) {
        intentBuilder.updateTodoList(targetDate = state.value.calendarState.selectedDate)
    }


    Box(
        modifier = modifier
            .fillMaxWidthOfScreen()
            .fillMaxHeight(),
    ) {
        var isExpanded by remember { mutableStateOf(false) }
        FabLayout(
            isExpanded = isExpanded,
            todoItemList = Todo.previewDummy,
            onClickFabButton = { isExpanded = isExpanded.not() },
            onClickTodo = { index: Int ->
                println(Todo.previewDummy[index])
            },
            modifier = Modifier
                .zIndex(FabZIndex)
                .align(Alignment.BottomEnd)
                .padding(bottom = 20.dp, end = 20.dp),
            onClickDirectInput = {},
            onClickDim = { isExpanded = isExpanded.not() },
        )

        CalendarScreen(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = NWKTheme.spacing.space200),
            calendarUiState = state,
            onToggleStateChanged = intentBuilder::updateCalendarModeWithToggleState,
            onClickToggle = intentBuilder::updateCalendarMode,
            onClickDateOfWeek = intentBuilder::updateTargetDate,
            onClickYearMonthButton = {},
            onClickCheckBox = intentBuilder::changeCompleteSchedule,
            onClickOptionButton = {},
            todoList = state.value.selectedTodoList,
        )
    }
}

@Preview
@Composable
private fun CalendarRoutePreview() {
    NWKTheme {
        CalendarRoute(
            modifier = Modifier.fillMaxSize(),
            navigateToDetailDate = {}
        )
    }
}
