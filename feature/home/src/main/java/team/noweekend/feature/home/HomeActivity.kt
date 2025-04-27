package team.noweekend.feature.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import team.noweekend.core.navigator.feature.SampleNavigator
import team.noweekend.feature.home.screen.HomeScreen
import team.noweekend.feature.home.theme.NoWeekendTheme
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {

    @Inject
    lateinit var sampleNavigator: SampleNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoWeekendTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomeScreen(
                        modifier = Modifier.padding(innerPadding),
                        onNavigateButtonClick = {
                            sampleNavigator.navigate(
                                activity = this@HomeActivity,
                                intentBuilder = null,
                            )
                        }
                    )
                }
            }
        }
    }
}
