package team.noweekend.core.common.android.extension

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.activity.ComponentActivity

inline fun <reified T : ComponentActivity> Context.getIntent(
    intentBuilder: (Intent.() -> Intent),
): Intent {
    return intentBuilder(Intent(this, T::class.java))
}

fun Context.showToast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun Context.showToast(textResId: Int) {
    Toast.makeText(this, textResId, Toast.LENGTH_SHORT).show()
}
