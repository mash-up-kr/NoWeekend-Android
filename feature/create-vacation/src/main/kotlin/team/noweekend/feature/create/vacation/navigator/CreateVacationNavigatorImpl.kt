package team.noweekend.feature.create.vacation.navigator

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import team.noweekend.core.common.android.extension.getIntent
import team.noweekend.core.navigator.feature.CreateVacationNavigator
import team.noweekend.feature.create.vacation.CreateVacationActivity
import javax.inject.Inject

class CreateVacationNavigatorImpl @Inject constructor() : CreateVacationNavigator {
    override fun navigateWithLauncher(
        activity: ComponentActivity,
        intentBuilder: (Intent.() -> Intent)?,
        launcher: ActivityResultLauncher<Intent>?,
    ) {
        if (launcher == null) {
            activity.startActivity(activity.getIntent<CreateVacationActivity>(intentBuilder ?: { this }))
        } else {
            launcher.launch(activity.getIntent<CreateVacationActivity>(intentBuilder ?: { this }))
        }
    }
}
