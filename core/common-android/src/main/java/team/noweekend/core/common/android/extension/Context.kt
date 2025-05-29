package team.noweekend.core.common.android.extension

import android.content.Context
import android.content.Intent
import androidx.activity.ComponentActivity

inline fun <reified T : ComponentActivity> Context.getIntent(
    intentBuilder: (Intent.() -> Intent),
): Intent {
    return intentBuilder(Intent(this, T::class.java))
}
