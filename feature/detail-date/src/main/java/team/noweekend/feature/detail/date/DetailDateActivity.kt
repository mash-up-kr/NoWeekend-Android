package team.noweekend.feature.detail.date

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import team.noweekend.feature.detail.date.navigation.DetailDateNavHost

@AndroidEntryPoint
class DetailDateActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            DetailDateNavHost(
                modifier = Modifier.fillMaxSize(),
                onClickBackButton = ::finish,
            )
        }
    }
}
