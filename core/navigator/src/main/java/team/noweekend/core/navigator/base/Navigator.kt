package team.noweekend.core.navigator.base

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher

/**
 * feature 모듈 간 Activity 전환에 사용하는 interface
 */
interface Navigator {
    /**
     * Activity 전환에 사용되는 메서드
     * ``` kotlin
     * SomeComposable(
     *     onClick = {
     *         someNavigator.navigate(
     *             activity = this@MainActivity,
     *             intentBuilder = {
     *                 putExtra(EXTRA_FLAG, true)
     *             }
     *         )
     *     }
     * )
     * ```
     * @param activity 현재 Activity Context
     * @param intentBuilder 타겟 Activity에 전달하는 intent
     */
    fun navigate(
        activity: ComponentActivity,
        intentBuilder: (Intent.() -> Intent)? = { this },
    ) = navigateWithLauncher(activity, intentBuilder, null)

    /**
     * Activity 전환 및 결과 전달에 사용되는 메서드
     * ``` kotlin
     * // Route-side
     * val someLauncher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
     *     if (result.resultCode == RESULT_OK) {
     *         // do something with result
     *     }
     * }
     *
     * // Sender activity-side
     * SenderComposable(
     *     onClick = {
     *         someNavigator.navigateWithLauncher(
     *             activity = this@MainActivity,
     *             intentBuilder = {
     *                 putExtra(EXTRA_FLAG, true)
     *             },
     *             launcher = someLauncher
     *         )
     *     }
     * )
     *
     * // Receiver activity-side (or Route-side)
     * ReceiverComposable(
     *     onClick = {
     *         setResult(Activity.RESULT_OK)
     *         finish()
     *     }
     * )
     *
     * ```
     * @param activity 현재 Activity Context
     * @param intentBuilder 타겟 Activity에 전달하는 [Intent]
     * @param launcher 타켓 Activity로 이동 후 결과를 전달 받을 [ActivityResultLauncher]
     */
    fun navigateWithLauncher(
        activity: ComponentActivity,
        intentBuilder: (Intent.() -> Intent)? = { this },
        launcher: ActivityResultLauncher<Intent>?,
    )
}
