package team.noweekend.feature.main.navigator

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import team.noweekend.core.common.android.extension.getIntent
import team.noweekend.core.navigator.feature.MainNavigator
import team.noweekend.feature.main.MainActivity
import javax.inject.Inject

class MainNavigatorImpl @Inject constructor() : MainNavigator {
    override fun navigateWithLauncher(
        activity: ComponentActivity,
        intentBuilder: (Intent.() -> Intent)?,
        launcher: ActivityResultLauncher<Intent>?,
    ) {
        if (launcher == null) {
            activity.startActivity(activity.getIntent<MainActivity>(intentBuilder ?: { this }))
        } else {
            launcher.launch(activity.getIntent<MainActivity>(intentBuilder ?: { this }))
        }
    }
}
