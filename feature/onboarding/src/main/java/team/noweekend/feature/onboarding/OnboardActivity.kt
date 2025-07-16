package team.noweekend.feature.onboarding

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import team.noweekend.core.navigator.feature.MainNavigator
import javax.inject.Inject

@AndroidEntryPoint
class OnboardActivity : ComponentActivity() {
    @Inject
    lateinit var mainNavigator: MainNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OnboardNavHost(
                navigateToHome = {
                    mainNavigator.navigate(this)
                    finish()
                },
            )
        }
    }
}
