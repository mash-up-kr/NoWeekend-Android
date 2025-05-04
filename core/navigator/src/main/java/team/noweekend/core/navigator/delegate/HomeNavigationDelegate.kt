package team.noweekend.core.navigator.delegate

import android.content.Context
import android.content.Intent
import androidx.activity.ComponentActivity
import dagger.hilt.android.qualifiers.ActivityContext
import team.noweekend.core.navigator.feature.sample.SampleNavigator
import javax.inject.Inject

class HomeNavigationDelegate @Inject constructor(
    @ActivityContext private val context: Context,
    private val sampleNavigator: SampleNavigator,
) {
    fun navigateToSample(
        activity: ComponentActivity = context as ComponentActivity,
        intentBuilder: (Intent.() -> Intent)? = null,
    ) {
        sampleNavigator.navigate(
            activity = activity,
            intentBuilder = intentBuilder,
        )
    }
}
