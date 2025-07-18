package team.noweekend.feature.main

import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import team.noweekend.core.navigator.model.Calendar
import team.noweekend.feature.calendar.navigation.calendarNavGraph
import team.noweekend.feature.home.navigation.homeNavGraph
import team.noweekend.feature.main.navigation.MainNavigator
import team.noweekend.feature.profile.navigation.profileNavGraph

@Composable
internal fun MainNavHost(
    navigateToExternalWebBrowser: (String) -> Unit,
    navigateToCreateVacation: ((Intent.() -> Intent)?, ActivityResultLauncher<Intent>?) -> Unit,
    navigateToDetailDate: ((Intent.() -> Intent)?) -> Unit,
    navigateToAddTodo: ((Intent.() -> Intent)?) -> Unit,
    navigateToAddTodoWithDirectInput: ((Intent.() -> Intent)?) -> Unit,
    navigateToEditTodo: ((Intent.() -> Intent)?) -> Unit,
    navigator: MainNavigator,
    modifier: Modifier = Modifier,
) {

    NavHost(
        navController = navigator.navController,
        startDestination = navigator.startDestination,
        modifier = modifier,
    ) {
        homeNavGraph(
            navigateToCreateVacation = navigateToCreateVacation,
            navigateToCalendar = { date ->
                navigator.navController.navigate(Calendar)
            },
        )
        calendarNavGraph(
            navigateToDetailDate = { dateString ->
                navigateToDetailDate(
                    {
                        putExtra("date", dateString)
                    },
                )
            },
            navigateToAddTodo = {
                navigateToAddTodo(
                    {
                        putExtra("type", "")
                    },
                )
            },
            navigateToAddTodoWithDirectInput = {
                navigateToAddTodoWithDirectInput(
                    { this },
                )
            },
            navigateToEditTodo = { schedule ->
                val json = Json.encodeToString(schedule)
                println("schedule: $json")
                navigateToEditTodo(
                    {
                        putExtra("id", json)
                    },
                )
            },
        )
        profileNavGraph(
            navigateToExternalWebBrowser = navigateToExternalWebBrowser,
        )
    }
}
