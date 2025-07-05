package team.noweekend.feature.calendar.screen

import NWKCalender
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.datetime.LocalDate
import team.noweekend.core.common.ui.calendar.model.DateOfWeek
import team.noweekend.core.common.ui.calendar.model.WeeksData
import team.noweekend.core.common.ui.calendar.state.CalendarPagerState.CalendarMode
import team.noweekend.core.common.ui.todo.model.Todo
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.feature.calendar.component.choose.YearMonthCalendarTypeChooser
import team.noweekend.feature.calendar.component.todoList.CalendarTodoList

@Composable
internal fun CalendarScreen(
    mode: CalendarMode,
    weekPagerState: PagerState,
    monthPagerState: PagerState,
    selectedDate: State<LocalDate>,
    chooserDate: State<LocalDate>,
    monthData: SnapshotStateMap<Int, WeeksData>,
    weeksData: SnapshotStateMap<Int, WeeksData>,
    onClickDateOfWeek: (DateOfWeek) -> Unit,
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
            calendarMode = mode,
            onClickToggle = onClickToggle,
            onClickYearMonthButton = onClickYearMonthButton,
            onToggleStateChanged = onToggleStateChanged,
        )
        NWKCalender(
            mode = mode,
            weekPagerState = weekPagerState,
            monthPagerState = monthPagerState,
            selectedDate = selectedDate,
            monthData = monthData,
            weeksData = weeksData,
            calendarItemClickable = true,
            onClickDateOfWeek = onClickDateOfWeek,
        )
        if(mode == CalendarMode.WEEK) {
            CalendarTodoList(
                todoList = todoList,
                onClickCheckBox = onClickCheckBox,
                onClickOptionButton = onClickOptionButton,
            )
        }
    }
}
