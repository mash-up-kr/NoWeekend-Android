package team.noweekend.feature.create.vacation.information.mvi

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import team.noweekend.core.common.android.mvi.SideEffect
import team.noweekend.core.common.android.mvi.SideEffectHandler

@Composable
internal fun rememberInformationSideEffectHandler(
    navigateToHistoryBack: () -> Unit,
    navigateToHome: (Int, String, String, String, String) -> Unit,
): InformationSideEffectHandler = remember {
    InformationSideEffectHandler(
        navigateToHistoryBack = navigateToHistoryBack,
        navigateToHome = navigateToHome,
    )
}

internal class InformationSideEffectHandler(
    private val navigateToHistoryBack: () -> Unit,
    private val navigateToHome: (Int, String, String, String, String) -> Unit,
) : SideEffectHandler<SideEffect> {
    override fun handleSideEffect(sideEffect: SideEffect) {
        when (sideEffect) {
            is InformationSideEffect.NavigateToHistoryBack -> navigateToHistoryBack()
            is InformationSideEffect.NavigateToHome ->{
                navigateToHome(
                    sideEffect.days,
                    sideEffect.travelStyle,
                    sideEffect.activityType,
                    sideEffect.restPreference,
                    sideEffect.leisurePreference,
                )
            }
        }
    }
}
