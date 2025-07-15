package team.noweekend.core.navigator.delegate

import android.content.Context
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import dagger.hilt.android.qualifiers.ActivityContext
import team.noweekend.core.navigator.feature.CreateVacationNavigator
import team.noweekend.core.navigator.feature.DetailDateNavigator
import javax.inject.Inject

class HomeNavigationDelegate @Inject constructor(
    @ActivityContext private val context: Context,
    private val createVacationNavigator: CreateVacationNavigator,
    private val detailDateNavigator: DetailDateNavigator
) {
    fun navigateToCreateVacation(
        activity: ComponentActivity = context as ComponentActivity,
        intentBuilder: (Intent.() -> Intent)? = null,
        launcher: ActivityResultLauncher<Intent>?,
    ) {
        createVacationNavigator.navigateWithLauncher(
            activity = activity,
            intentBuilder = intentBuilder,
            launcher = launcher,
        )
    }

    fun navigateToDetailDate(
        activity: ComponentActivity = context as ComponentActivity,
        intentBuilder: (Intent.() -> Intent)? = null,
        launcher: ActivityResultLauncher<Intent>?,
    ){
        detailDateNavigator.navigateWithLauncher(
            activity = activity,
            intentBuilder = intentBuilder,
            launcher = launcher
        )
    }
}
