package team.noweekend.feature.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import team.noweekend.core.design.system.foundation.NoWeekendTheme
import team.noweekend.core.navigator.delegate.HomeNavigationDelegate
import team.noweekend.core.navigator.extra.FLAG_SHOW_BUTTON
import team.noweekend.feature.home.screen.HomeScreen
import javax.inject.Inject
import kotlin.random.Random

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {

    @Inject
    lateinit var homeNavigationDelegate: HomeNavigationDelegate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoWeekendTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomeScreen(
                        modifier = Modifier.padding(innerPadding),
                        onNavigateButtonClick = {
                            homeNavigationDelegate.navigateToSample(
                                intentBuilder = {
                                    putExtra(FLAG_SHOW_BUTTON, Random.nextBoolean())
                                }
                            )
                        }
                    )
                }
            }
        }
    }
}
