package team.noweekend.core.navigator

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher

interface BaseNavigator {
    fun navigate(
        activity: ComponentActivity,
        intentBuilder: (Intent.() -> Intent)? = { this },
    ) = navigateWithLauncher(activity, intentBuilder, null)

    fun navigateWithLauncher(
        activity: ComponentActivity,
        intentBuilder: (Intent.() -> Intent)? = { this },
        launcher: ActivityResultLauncher<Intent>?,
    )
}
