package team.noweekend.catalog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import team.noweekend.core.design.system.foundation.theme.NWKTheme

@AndroidEntryPoint
class CatalogActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NWKTheme {
                CatalogNavHost()
            }
        }
    }
}
