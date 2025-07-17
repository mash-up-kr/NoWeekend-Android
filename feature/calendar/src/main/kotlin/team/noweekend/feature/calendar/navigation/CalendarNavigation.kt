package team.noweekend.feature.calendar.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import kotlinx.serialization.InternalSerializationApi
import team.noweekend.core.common.ui.todo.model.Todo
import team.noweekend.core.navigator.model.Calendar
import team.noweekend.feature.calendar.screen.CalendarRoute

@OptIn(InternalSerializationApi::class)
fun NavHostController.navigateToCalendar(date: String) {
    navigate(Calendar)
}

@OptIn(InternalSerializationApi::class)
fun NavGraphBuilder.calendarNavGraph(
    navigateToDetailDate: (String) -> Unit,
    navigateToAddTodo: (Todo) -> Unit,
) {
    composable<Calendar> {
        CalendarRoute(
            navigateToDetailDate = navigateToDetailDate,
            navigateToAddTodo = navigateToAddTodo,
        )
    }
}
