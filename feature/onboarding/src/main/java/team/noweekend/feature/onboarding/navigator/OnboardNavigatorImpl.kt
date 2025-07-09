package team.noweekend.feature.onboarding.navigator

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import team.noweekend.core.common.android.extension.getIntent
import team.noweekend.core.navigator.feature.OnboardNavigator
import team.noweekend.feature.onboarding.OnboardActivity
import javax.inject.Inject

class OnboardNavigatorImpl @Inject constructor() : OnboardNavigator {
    override fun navigateWithLauncher(
        activity: ComponentActivity,
        intentBuilder: (Intent.() -> Intent)?,
        launcher: ActivityResultLauncher<Intent>?,
    ) {
        if (launcher == null) {
            activity.startActivity(activity.getIntent<OnboardActivity>(intentBuilder ?: { this }))
        } else {
            launcher.launch(activity.getIntent<OnboardActivity>(intentBuilder ?: { this }))
        }
    }
}
