package team.noweekend.feature.splash

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.core.navigator.feature.LoginNavigator
import team.noweekend.core.navigator.feature.MainNavigator
import team.noweekend.core.navigator.feature.OnboardNavigator
import team.noweekend.feature.splash.screen.SplashRoute
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : ComponentActivity() {

    @Inject
    lateinit var mainNavigator: MainNavigator

    @Inject
    lateinit var loginNavigator: LoginNavigator

    @Inject
    lateinit var onboardNavigator: OnboardNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NWKTheme {
                SplashRoute(
                    modifier = Modifier.fillMaxSize(),
                    navigateToMain = {
                        mainNavigator.navigate(this)
                        finish()
                    },
                    navigateToLogin = {
                        loginNavigator.navigate(this)
                        finish()
                    },
                    navigateToOnboarding = {
                        onboardNavigator.navigate(this)
                        finish()
                    },
                )
            }
        }
    }
}
