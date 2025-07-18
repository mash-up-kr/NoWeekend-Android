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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import team.noweekend.core.common.android.extension.fillMaxWidthOfScreen
import team.noweekend.core.common.ui.fab.FabLayout
import team.noweekend.core.common.ui.todo.model.Todo
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.core.model.schedule.Schedule
import team.noweekend.feature.calendar.component.bottomsheet.MonthChooserBottomSheet
import team.noweekend.feature.calendar.component.bottomsheet.TodoBottomSheet
import team.noweekend.feature.calendar.model.StableLocalDate
import team.noweekend.feature.calendar.mvi.CalendarViewModel
import team.noweekend.feature.calendar.mvi.builder.rememberIntentBuilder
import team.noweekend.feature.calendar.mvi.rememberSideEffectHandler

@Composable
internal fun CalendarRoute(
    navigateToDetailDate: (String) -> Unit,
    navigateToAddTodo: (Todo) -> Unit,
    navigateToAddTodoWithDirectInput: () -> Unit,
    navigateToEditTodo: (schedule: Schedule) -> Unit,
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
        navigateToDetailDate = navigateToDetailDate,
        navigateToAddTodo = navigateToAddTodo,
        navigateToAddTodoWithDirectInput = navigateToAddTodoWithDirectInput,
        navigateToEditTodo = navigateToEditTodo
    )



    LaunchedEffect(Unit) {
        calendarViewModel.sideEffect.collect(calendarSideEffectHandler::handleSideEffect)
    }
    LifecycleEventEffect(Lifecycle.Event.ON_START) {
        intentBuilder.updateCalendarState()
        with(intentBuilder) {
            collectCalendarEvent()
            updateCalendarData()
            getRecommendTodoTagList()
        }
    }
    LaunchedEffect(state.value.calendarMode) {
        with(intentBuilder) {
            initCalendarData()
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
            todoItemList = state.value.recommendTodoList,
            onClickFabButton = { isExpanded = isExpanded.not() },
            onClickTodo = intentBuilder::clickRecommendTodoTag,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 20.dp, end = 20.dp),
            onClickDirectInput = intentBuilder::clickDirectInput,
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
            onClickYearMonthButton = intentBuilder::clickMonthChooser,
            onClickCheckBox = intentBuilder::changeCompleteSchedule,
            onClickOptionButton = intentBuilder::clickTodoOption,
        )

        if (state.value.monthChooserVisible) {
            MonthChooserBottomSheet(
                initialDate = StableLocalDate(state.value.chooserMonth),
                onClickSelectButton = intentBuilder::initCalendarDateWithDate,
                onDismissRequest = intentBuilder::clickMonthChooser,
            )
        }

        if(state.value.todoOptionVisibility.visible){
            TodoBottomSheet(
                todoIndex = state.value.todoOptionVisibility.todoIndex,
                todoType = state.value.todoOptionVisibility.todoType,
                onDismissRequest = intentBuilder::dismissTodoOption,
                onClickAction = intentBuilder::clickAction,
            )
        }
    }
}

@Preview
@Composable
private fun CalendarRoutePreview() {
    NWKTheme {
        CalendarRoute(
            modifier = Modifier.fillMaxSize(),
            navigateToDetailDate = {},
            navigateToAddTodo = {},
            navigateToAddTodoWithDirectInput = {},
            navigateToEditTodo = {}
        )
    }
}
