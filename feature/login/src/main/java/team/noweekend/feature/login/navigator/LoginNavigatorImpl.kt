package team.noweekend.feature.login.navigator

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import team.noweekend.core.common.android.extension.getIntent
import team.noweekend.core.navigator.feature.LoginNavigator
import team.noweekend.feature.login.LoginActivity
import javax.inject.Inject

class LoginNavigatorImpl @Inject constructor() : LoginNavigator {
    override fun navigateWithLauncher(
        activity: ComponentActivity,
        intentBuilder: (Intent.() -> Intent)?,
        launcher: ActivityResultLauncher<Intent>?,
    ) {
        if (launcher == null) {
            activity.startActivity(activity.getIntent<LoginActivity>(intentBuilder ?: { this }))
        } else {
            launcher.launch(activity.getIntent<LoginActivity>(intentBuilder ?: { this }))
        }
    }
}
