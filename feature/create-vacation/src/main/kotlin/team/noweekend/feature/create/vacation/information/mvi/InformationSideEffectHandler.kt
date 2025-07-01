package team.noweekend.feature.create.vacation.information.mvi

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import team.noweekend.core.common.android.mvi.SideEffect
import team.noweekend.core.common.android.mvi.SideEffectHandler

@Composable
internal fun rememberInformationSideEffectHandler(
    navigateToHistoryBack: () -> Unit,
    navigateToVacationRecommendation: () -> Unit,
): InformationSideEffectHandler = remember {
    InformationSideEffectHandler(
        navigateToHistoryBack = navigateToHistoryBack,
        navigateToVacationRecommendation = navigateToVacationRecommendation,
    )
}

internal class InformationSideEffectHandler(
    private val navigateToHistoryBack: () -> Unit,
    private val navigateToVacationRecommendation: () -> Unit,
) : SideEffectHandler<SideEffect> {
    override fun handleSideEffect(sideEffect: SideEffect) {
        when (sideEffect) {
            InformationSideEffect.NavigateToHistoryBack -> navigateToHistoryBack()
            InformationSideEffect.NavigateToVacationRecommendation -> navigateToVacationRecommendation()
        }
    }
}
