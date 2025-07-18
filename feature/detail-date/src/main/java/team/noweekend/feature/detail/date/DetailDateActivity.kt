package team.noweekend.feature.detail.date

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.json.Json
import team.noweekend.core.common.android.mvi.Intent
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.core.navigator.delegate.CalendarNavigationDelegate
import team.noweekend.core.navigator.model.DetailDate
import team.noweekend.feature.detail.date.navigation.DetailDateNavHost
import javax.inject.Inject

@AndroidEntryPoint
class DetailDateActivity : ComponentActivity() {

    @Inject
    lateinit var calendarNavigationDelegate: CalendarNavigationDelegate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            NWKTheme {
                val date = intent.getStringExtra("date") ?: ""
                val todoList: String = intent.getStringExtra("todoList") ?: ""
                DetailDateNavHost(
                    startDestination = DetailDate(date = date, todoList = todoList),
                    modifier = Modifier.fillMaxSize(),
                    navigateToBack = ::finish,
                    navigateToAddTodo = {
                        calendarNavigationDelegate.navigateToAddTask(
                            activity = this,
                            launcher = null,
                        )
                    },
                    navigateToEditTodo = {
                        val json = Json.encodeToString(it)
                        calendarNavigationDelegate.navigateToAddTask(
                            activity = this,
                            intentBuilder = {
                                android.content.Intent().apply {
                                    putExtra("id", json)
                                }
                            },
                            launcher = null,
                        )
                    },
                    navigateToAddTodoWithDirectInput = {
                        calendarNavigationDelegate.navigateToAddTask(
                            activity = this,
                            launcher = null,
                        )
                    },
                )
            }
        }
    }
}
