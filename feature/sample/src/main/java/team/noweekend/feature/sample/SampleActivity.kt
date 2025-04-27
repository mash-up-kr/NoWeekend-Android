package team.noweekend.feature.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import team.noweekend.feature.sample.screen.SampleScreen
import team.noweekend.feature.sample.theme.NoWeekendTheme

@AndroidEntryPoint
class SampleActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoWeekendTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                ) { innerPadding ->
                    SampleScreen(
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }
}
