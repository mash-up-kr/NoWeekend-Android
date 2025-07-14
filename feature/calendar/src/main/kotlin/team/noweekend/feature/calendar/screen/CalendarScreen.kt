package team.noweekend.feature.calendar.screen

import NWKCalender
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.datetime.LocalDate
import team.noweekend.core.common.ui.calendar.model.CalendarDateOfWeek
import team.noweekend.core.common.ui.calendar.model.CalendarMode
import team.noweekend.core.common.ui.calendar.model.CalendarState
import team.noweekend.core.common.ui.todo.model.Todo
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.feature.calendar.component.choose.YearMonthCalendarTypeChooser
import team.noweekend.feature.calendar.component.todoList.CalendarTodoList

@Composable
internal fun CalendarScreen(
    chooserDate: State<LocalDate>,
    calendarState: CalendarState,
    onClickDateOfWeek: (CalendarDateOfWeek) -> Unit,
    onClickYearMonthButton: () -> Unit,
    onClickToggle: () -> Unit,
    onToggleStateChanged: (Boolean) -> Unit,
    onClickCheckBox: (Int) -> Unit,
    onClickOptionButton: (Int) -> Unit,
    modifier: Modifier = Modifier,
    todoList: ImmutableList<Todo> = persistentListOf(),
) {


    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = NWKTheme.color.Neutral.white),
    ) {
        YearMonthCalendarTypeChooser(
            date = chooserDate,
            calendarMode = calendarState.mode,
            onClickToggle = onClickToggle,
            onClickYearMonthButton = onClickYearMonthButton,
            onToggleStateChanged = onToggleStateChanged,
        )
        NWKCalender(
            calendarState = calendarState,
            onClickDateOfWeek = onClickDateOfWeek,
            userScrollEnabled = true,
        )
        if (calendarState.mode == CalendarMode.WEEK) {
            CalendarTodoList(
                todoList = todoList,
                onClickCheckBox = onClickCheckBox,
                onClickOptionButton = onClickOptionButton,
            )
        }
    }
}
