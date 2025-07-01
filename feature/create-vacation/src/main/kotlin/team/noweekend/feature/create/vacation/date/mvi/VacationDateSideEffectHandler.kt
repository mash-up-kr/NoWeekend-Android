package team.noweekend.feature.create.vacation.date.mvi

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import team.noweekend.core.common.android.mvi.SideEffect
import team.noweekend.core.common.android.mvi.SideEffectHandler

@Composable
internal fun rememberVacationDateSideEffectHandler(
    navigateToHistoryBack: () -> Unit,
    navigateToInformation: () -> Unit,
): VacationDateSideEffectHandler {
    return remember {
        VacationDateSideEffectHandler(
            navigateToHistoryBack = navigateToHistoryBack,
            navigateToInformation = navigateToInformation,
        )
    }
}

internal class VacationDateSideEffectHandler(
    private val navigateToHistoryBack: () -> Unit,
    private val navigateToInformation: () -> Unit,
) : SideEffectHandler<SideEffect> {
    override fun handleSideEffect(sideEffect: SideEffect) {
        when (sideEffect) {
            is VacationDateSideEffect.NavigateToHistoryBack -> navigateToHistoryBack()
            is VacationDateSideEffect.NavigateToInformation -> navigateToInformation()
        }
    }
}
