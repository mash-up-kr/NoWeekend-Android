package team.noweekend.core.navigator.delegate

import android.content.Context
import android.content.Intent
import androidx.activity.ComponentActivity
import dagger.hilt.android.qualifiers.ActivityContext
import team.noweekend.core.navigator.feature.CreateVacationNavigator
import javax.inject.Inject

class HomeNavigationDelegate @Inject constructor(
    @ActivityContext private val context: Context,
    private val createVacationNavigator: CreateVacationNavigator,
) {
    fun navigateToCreateVacation(
        activity: ComponentActivity = context as ComponentActivity,
        intentBuilder: (Intent.() -> Intent)? = null,
    ) {
        createVacationNavigator.navigate(
            activity = activity,
            intentBuilder = intentBuilder,
        )
    }
}
