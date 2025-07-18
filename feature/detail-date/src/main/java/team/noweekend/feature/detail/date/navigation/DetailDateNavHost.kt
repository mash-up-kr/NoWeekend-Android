package team.noweekend.feature.detail.date.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.InternalSerializationApi
import team.noweekend.core.common.ui.todo.model.Todo
import team.noweekend.core.design.system.core.component.scaffold.NWKScaffold
import team.noweekend.core.model.schedule.Schedule
import team.noweekend.core.navigator.model.DetailDate
import team.noweekend.feature.detail.date.screen.DetailDateRoute

@OptIn(InternalSerializationApi::class)
@Composable
fun DetailDateNavHost(
    startDestination: DetailDate,
    navigateToBack: () -> Unit,
    navigateToAddTodo: (Todo) -> Unit,
    navigateToAddTodoWithDirectInput: () -> Unit,
    navigateToEditTodo: (Schedule) -> Unit,
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()
    NWKScaffold(
        modifier = modifier,
    ) {
        NavHost(
            navController = navController,
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            startDestination = startDestination,
        ) {
            composable<DetailDate> {
                DetailDateRoute(
                    modifier = Modifier.fillMaxSize(),
                    navigateToBack = navigateToBack,
                    navigateToAddTodoWithDirectInput = navigateToAddTodoWithDirectInput,
                    navigateToEditTodo = navigateToEditTodo,
                    navigateToAddTodo = navigateToAddTodo,
                )
            }
        }
    }
}
