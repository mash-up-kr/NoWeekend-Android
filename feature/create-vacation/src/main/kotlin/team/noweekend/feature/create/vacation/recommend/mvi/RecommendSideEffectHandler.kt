package team.noweekend.feature.create.vacation.recommend.mvi

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import team.noweekend.core.common.android.mvi.SideEffect
import team.noweekend.core.common.android.mvi.SideEffectHandler

@Composable
internal fun rememberRecommendSideEffectHandler(
    navigateToHistoryBack: () -> Unit,
): RecommendSideEffectHandler {
    return remember {
        RecommendSideEffectHandler(
            navigateToHistoryBack = navigateToHistoryBack,
        )
    }

}

internal class RecommendSideEffectHandler(
    private val navigateToHistoryBack: () -> Unit,
) : SideEffectHandler<SideEffect> {
    override fun handleSideEffect(sideEffect: SideEffect) {
        when (sideEffect) {
            is RecommendSideEffect.NavigateToHistoryBack -> navigateToHistoryBack()
        }
    }
}
