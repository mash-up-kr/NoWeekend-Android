package team.noweekend.catalog.example.mvi

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import team.noweekend.core.common.android.mvi.SideEffect
import team.noweekend.core.common.android.mvi.SideEffectHandler

@Composable
internal fun rememberExampleSideEffectHandler(
    navigateToHistoryBack: () -> Unit,
): ExampleSideEffectHandler {
    return remember {
        ExampleSideEffectHandler(
            navigateToHistoryBack = navigateToHistoryBack,
        )
    }
}

internal class ExampleSideEffectHandler(
    private val navigateToHistoryBack: () -> Unit,
) : SideEffectHandler<SideEffect> {
    override fun handleSideEffect(sideEffect: SideEffect) {
        when (sideEffect) {
            is ExampleSideEffect.NavigateToHistoryBack -> navigateToHistoryBack()
        }
    }
}
