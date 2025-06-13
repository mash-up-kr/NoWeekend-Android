package team.noweekend

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.navigation.NoWeekendNavHost

@AndroidEntryPoint
class NoWeekendActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NWKTheme {
                NoWeekendNavHost()
            }
        }
    }
}
