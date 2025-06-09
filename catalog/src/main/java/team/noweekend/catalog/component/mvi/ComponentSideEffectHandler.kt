package team.noweekend.catalog.component.mvi

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import team.noweekend.core.common.android.mvi.SideEffect
import team.noweekend.core.common.android.mvi.SideEffectHandler

@Composable
internal fun rememberComponentSideEffectHandler(
    navigateToHistoryBack: () -> Unit,
    navigateToExample: (Int, Int) -> Unit,
): ComponentSideEffectHandler {
    return remember {
        ComponentSideEffectHandler(
            navigateToHistoryBack = navigateToHistoryBack,
            navigateToExample = navigateToExample,
        )
    }
}

internal class ComponentSideEffectHandler(
    private val navigateToHistoryBack: () -> Unit,
    private val navigateToExample: (Int, Int) -> Unit,
) : SideEffectHandler<SideEffect> {
    override fun handleSideEffect(sideEffect: SideEffect) {
        when (sideEffect) {
            is ComponentSideEffect.NavigateToHistoryBack -> navigateToHistoryBack()
            is ComponentSideEffect.NavigateToExampleDetail -> {
                navigateToExample(
                    sideEffect.componentId,
                    sideEffect.exampleIndex,
                )
            }
        }
    }
}
