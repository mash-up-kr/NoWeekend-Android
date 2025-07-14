package team.noweekend.feature.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.core.navigator.delegate.HomeNavigationDelegate
import team.noweekend.feature.main.screen.MainRoute
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var homeNavigationDelegate: HomeNavigationDelegate

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
                )
            }
        }
    }
}
