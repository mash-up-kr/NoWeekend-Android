package team.noweekend.feature.sample.navigator

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import team.noweekend.core.common.android.extension.getIntent
import team.noweekend.core.navigator.feature.sample.SampleNavigator
import team.noweekend.feature.sample.SampleActivity
import javax.inject.Inject

internal class SampleNavigatorImpl @Inject constructor() : SampleNavigator {
    override fun navigateWithLauncher(
        activity: ComponentActivity,
        intentBuilder: (Intent.() -> Intent)?,
        launcher: ActivityResultLauncher<Intent>?
    ) {
        if (launcher == null) {
            activity.startActivity(activity.getIntent<SampleActivity>(intentBuilder ?: { this }))
        } else {
            launcher.launch(activity.getIntent<SampleActivity>(intentBuilder ?: { this }))
        }
    }
}
