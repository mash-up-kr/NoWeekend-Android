package team.noweekend.feature.calendar.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import team.noweekend.core.common.ui.todo.model.Todo
import team.noweekend.core.navigator.model.Calendar
import team.noweekend.feature.calendar.screen.CalendarRoute

fun NavGraphBuilder.calendarNavGraph(
    navigateToDetailDate: (String) -> Unit,
    navigateToAddTodo: (Todo) -> Unit,
    navigateToAddTodoWithDirectInput: () -> Unit,
) {
    composable<Calendar> {
        CalendarRoute(
            navigateToDetailDate = navigateToDetailDate,
            navigateToAddTodo = navigateToAddTodo,
            navigateToAddTodoWithDirectInput = navigateToAddTodoWithDirectInput,
        )
    }
}
