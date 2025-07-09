package team.noweekend.core.navigator.delegate

import android.content.Context
import android.content.Intent
import androidx.activity.ComponentActivity
import dagger.hilt.android.qualifiers.ActivityContext
import team.noweekend.core.navigator.feature.OnboardNavigator
import javax.inject.Inject

class LoginNavigationDelegate @Inject constructor(
    @ActivityContext private val context: Context,
    private val onboardNavigator: OnboardNavigator,
) {

    fun navigateToOnboard(
        activity: ComponentActivity = context as ComponentActivity,
        intentBuilder: (Intent.() -> Intent)? = null,
    ) {
        onboardNavigator.navigate(
            activity = activity,
            intentBuilder = intentBuilder,
        )
    }
}
