package team.noweekend.feature.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import team.noweekend.core.navigator.extra.FLAG_SHOW_BUTTON
import team.noweekend.feature.sample.theme.NoWeekendTheme

@AndroidEntryPoint
class SampleActivity : ComponentActivity() {

    private val showButton by lazy { intent.getBooleanExtra(FLAG_SHOW_BUTTON, false) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NoWeekendTheme {
                Column {
                    SampleNavHost(modifier = Modifier.weight(1f))
                    if (showButton) {
                        Button(
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            onClick = { finish() },
                        ) {
                            Text("Go To Home")
                        }
                    }
                }
            }
        }
    }
}
