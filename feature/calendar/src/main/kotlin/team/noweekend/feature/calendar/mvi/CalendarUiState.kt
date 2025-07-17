package team.noweekend.feature.calendar.mvi

import androidx.compose.runtime.Immutable
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.ImmutableMap
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.persistentMapOf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.datetime.LocalDate
import team.noweekend.core.common.android.mvi.UiState
import team.noweekend.core.common.kotlin.extension.now
import team.noweekend.core.common.ui.calendar.model.CalendarMode
import team.noweekend.core.common.ui.calendar.model.CalendarState
import team.noweekend.core.common.ui.calendar.state.CalendarPagerState
import team.noweekend.core.common.ui.todo.model.Todo
import team.noweekend.feature.calendar.model.CalendarWeeksDataWithTodoList


@Immutable
data class CalendarUiState(
    val chooserMonth: LocalDate,
    val calendarMode: CalendarMode,
    val calendarWeeksData: StateFlow<ImmutableMap<Int, CalendarWeeksDataWithTodoList>>,
    val calendarMonthsData: StateFlow<ImmutableMap<Int, CalendarWeeksDataWithTodoList>>,
    val selectedTodoList: StateFlow<ImmutableList<Todo>>,
    val calendarPagerState: CalendarPagerState,
    val calendarState: CalendarState,
    val recommendTodoList: ImmutableList<Todo>,
    val monthChooserVisible: Boolean = false,
) : UiState {
    companion object {
        val Init = CalendarUiState(
            chooserMonth = LocalDate.now(),
            calendarWeeksData = MutableStateFlow(persistentMapOf()),
            calendarMonthsData = MutableStateFlow(persistentMapOf()),
            calendarMode = CalendarMode.WEEK,
            selectedTodoList = MutableStateFlow(persistentListOf()),
            calendarPagerState = CalendarPagerState(),
            calendarState = CalendarState.Week.default,
            recommendTodoList = persistentListOf(),
        )
    }
}
