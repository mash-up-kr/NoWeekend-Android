package team.noweekend.feature.detail.date.navigator

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import team.noweekend.core.common.android.extension.getIntent
import team.noweekend.core.navigator.feature.DetailDateNavigator
import team.noweekend.feature.detail.date.DetailDateActivity
import javax.inject.Inject

class DetailDateNavigatorImpl @Inject constructor() : DetailDateNavigator{


    override fun navigateWithLauncher(
        activity: ComponentActivity,
        intentBuilder: (Intent.() -> Intent)?,
        launcher: ActivityResultLauncher<Intent>?
    ) {
        if (launcher == null) {
            activity.startActivity(activity.getIntent<DetailDateActivity>(intentBuilder ?: { this }))
        } else {
            launcher.launch(activity.getIntent<DetailDateActivity>(intentBuilder ?: { this }))
        }
    }
}
