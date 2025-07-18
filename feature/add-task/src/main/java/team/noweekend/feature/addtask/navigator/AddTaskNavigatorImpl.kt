package team.noweekend.feature.addtask.navigator

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import team.noweekend.core.common.android.extension.getIntent
import team.noweekend.core.navigator.feature.AddTaskNavigator
import team.noweekend.feature.addtask.AddTaskActivity
import javax.inject.Inject

class AddTaskNavigatorImpl @Inject constructor() : AddTaskNavigator {
    override fun navigateWithLauncher(
        activity: ComponentActivity,
        intentBuilder: (Intent.() -> Intent)?,
        launcher: ActivityResultLauncher<Intent>?,
    ) {
        if (launcher == null) {
            activity.startActivity(activity.getIntent<AddTaskActivity>(intentBuilder ?: { this }))
        } else {
            launcher.launch(activity.getIntent<AddTaskActivity>(intentBuilder ?: { this }))
        }
    }
}
