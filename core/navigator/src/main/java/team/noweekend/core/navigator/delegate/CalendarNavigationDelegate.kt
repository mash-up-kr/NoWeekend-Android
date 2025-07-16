package team.noweekend.core.navigator.delegate

import android.content.Context
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped
import team.noweekend.core.navigator.feature.DetailDateNavigator
import javax.inject.Inject

@ActivityScoped
class CalendarNavigationDelegate @Inject constructor(
    @ActivityContext private val context: Context,
    private val detailDateNavigator: DetailDateNavigator,
) {
    fun navigateToDetailDate(
        activity: ComponentActivity = context as ComponentActivity,
        intentBuilder: (Intent.() -> Intent)? = null,
        launcher: ActivityResultLauncher<Intent>?,
    ) {
        detailDateNavigator.navigateWithLauncher(
            activity = activity,
            intentBuilder = intentBuilder,
            launcher = launcher,
        )
    }

    fun navigateToAddTask(
        activity: ComponentActivity = context as ComponentActivity,
        intentBuilder: (Intent.() -> Intent)? = null,
        launcher: ActivityResultLauncher<Intent>?,
    ) {
    }
}
