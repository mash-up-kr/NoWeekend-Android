package team.noweekend.core.navigator

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher

interface BaseNavigator {
    fun navigateTo(
        activity: ComponentActivity,
        intentBuilder: (Intent.() -> Intent)? = { this },
    ) = navigateTo(activity, intentBuilder, null)

    fun navigateTo(
        activity: ComponentActivity,
        intentBuilder: (Intent.() -> Intent)? = { this },
        launcher: ActivityResultLauncher<Intent>?,
    )
}
