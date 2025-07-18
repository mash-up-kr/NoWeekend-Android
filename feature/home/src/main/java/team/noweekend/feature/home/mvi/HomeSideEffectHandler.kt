package team.noweekend.feature.home.mvi

import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import team.noweekend.core.common.android.mvi.SideEffect
import team.noweekend.core.common.android.mvi.SideEffectHandler

@Composable
internal fun rememberHomeSideEffectHandler(
    navigateToCreateVacation: ((Intent.() -> Intent)?, ActivityResultLauncher<Intent>?) -> Unit,
    navigateToCalendar: (String) -> Unit,
): HomeSideEffectHandler {
    return remember {
        HomeSideEffectHandler(
            navigateToCreateVacation = navigateToCreateVacation,
            navigateToCalendar = navigateToCalendar,
        )
    }
}

internal class HomeSideEffectHandler(
    private val navigateToCreateVacation: ((Intent.() -> Intent)?, ActivityResultLauncher<Intent>?) -> Unit,
    private val navigateToCalendar: (String) -> Unit,
) : SideEffectHandler<SideEffect> {
    override fun handleSideEffect(sideEffect: SideEffect) {
        when (sideEffect) {
            is HomeSideEffect.NavigateToCreateVacation -> navigateToCreateVacation(sideEffect.intentBuilder, null)
            is HomeSideEffect.NavigateToCalendar -> navigateToCalendar(sideEffect.startLocalDateTime)
        }
    }
}
