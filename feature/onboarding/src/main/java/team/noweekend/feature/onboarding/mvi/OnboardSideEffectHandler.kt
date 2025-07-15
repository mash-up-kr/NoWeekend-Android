package team.noweekend.feature.onboarding.mvi

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import team.noweekend.core.common.android.mvi.SideEffectHandler

@Composable
internal fun rememberOnboardSideEffectHandler(
    onNavigateToBack: () -> Unit,
    onNavigateToHome: () -> Unit,
    onNavigateToScheduleTag: () -> Unit,
    onNavigateToVacation: () -> Unit,
): OnboardSideEffectHandler {
    return remember {
        OnboardSideEffectHandler(
            onNavigateToBack = onNavigateToBack,
            onNavigateToHome = onNavigateToHome,
            onNavigateToScheduleTag = onNavigateToScheduleTag,
            onNavigateToVacation = onNavigateToVacation,
        )
    }
}

internal class OnboardSideEffectHandler(
    private val onNavigateToBack: () -> Unit,
    private val onNavigateToHome: () -> Unit,
    private val onNavigateToScheduleTag: () -> Unit,
    private val onNavigateToVacation: () -> Unit,
) : SideEffectHandler<OnboardSideEffect> {

    override fun handleSideEffect(sideEffect: OnboardSideEffect) {
        when (sideEffect) {
            is OnboardSideEffect.NavigateToBack -> onNavigateToBack()
            is OnboardSideEffect.NavigateToHome -> onNavigateToHome()
            is OnboardSideEffect.NavigateToScheduleTag -> onNavigateToScheduleTag()
            is OnboardSideEffect.NavigateToVacation -> onNavigateToVacation()
        }
    }
}
