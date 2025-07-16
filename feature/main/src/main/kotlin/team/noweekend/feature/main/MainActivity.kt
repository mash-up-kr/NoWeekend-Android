package team.noweekend.feature.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.net.toUri
import dagger.hilt.android.AndroidEntryPoint
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.core.navigator.delegate.CalendarNavigationDelegate
import team.noweekend.core.navigator.delegate.HomeNavigationDelegate
import team.noweekend.feature.main.screen.MainRoute
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var homeNavigationDelegate: HomeNavigationDelegate
    @Inject
    lateinit var calendarNavigationDelegate: CalendarNavigationDelegate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NWKTheme {
                MainRoute(
                    navigateToCreateVacation = { intentBuilder, launcher ->
                        homeNavigationDelegate.navigateToCreateVacation(
                            activity = this,
                            intentBuilder = intentBuilder,
                            launcher = launcher,
                        )
                    },
                    navigateToDetailDate = { intentBuilder ->
                        calendarNavigationDelegate.navigateToDetailDate(
                            activity = this,
                            intentBuilder = intentBuilder,
                            launcher = null,
                        )
                    },
                    navigateToExternalWebBrowser = ::openExternalWebBrowser,
                    navigateToAddTodo = {intentBuilder ->
                        /* Todo use CalendarNavigationDelgate
                          ex) calendarNavigationDelegate.navigateToAddTask()
                         */
                    }
                )
            }
        }
    }

    fun openExternalWebBrowser(url: String) {
        val intent = Intent().apply {
            action = Intent.ACTION_VIEW
            data = url.toUri()
        }

        startActivity(intent)
    }
}
