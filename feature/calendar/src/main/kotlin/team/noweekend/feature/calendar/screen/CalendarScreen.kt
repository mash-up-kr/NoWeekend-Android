package team.noweekend.feature.calendar.screen

import NWKCalender
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import team.noweekend.core.common.ui.calendar.model.CalendarDateOfWeek
import team.noweekend.core.common.ui.calendar.model.CalendarMode
import team.noweekend.core.common.ui.todo.model.Todo
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.feature.calendar.component.choose.YearMonthCalendarTypeChooser
import team.noweekend.core.common.ui.calendar.component.CalendarTodoList
import team.noweekend.feature.calendar.mvi.CalendarUiState

@Composable
internal fun CalendarScreen(
    calendarUiState: State<CalendarUiState>,
    onClickDateOfWeek: (CalendarDateOfWeek) -> Unit,
    onClickYearMonthButton: () -> Unit,
    onClickToggle: () -> Unit,
    onToggleStateChanged: (Boolean) -> Unit,
    onClickCheckBox: (Int) -> Unit,
    onClickOptionButton: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {

    val date = rememberUpdatedState(calendarUiState.value.chooserMonth)


    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = NWKTheme.color.Neutral.white),
    ) {
        YearMonthCalendarTypeChooser(
            date = date,
            calendarMode = calendarUiState.value.calendarMode,
            onClickToggle = onClickToggle,
            onClickYearMonthButton = onClickYearMonthButton,
            onToggleStateChanged = onToggleStateChanged,
        )
        NWKCalender(
            calendarState = calendarUiState.value.calendarState,
            onClickDateOfWeek = onClickDateOfWeek,
            userScrollEnabled = true,
        )
        if (calendarUiState.value.calendarMode == CalendarMode.WEEK) {
            val todoList = calendarUiState.value.selectedTodoList.collectAsStateWithLifecycle()
            LaunchedEffect(todoList.value) {
                println(todoList.value)
            }
            CalendarTodoList(
                todoList = todoList.value,
                onClickCheckBox = onClickCheckBox,
                onClickOptionButton = onClickOptionButton,
            )
        }
    }
}
