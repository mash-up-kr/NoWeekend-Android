package team.noweekend.catalog.home.mvi

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import team.noweekend.catalog.model.Component
import team.noweekend.core.common.android.mvi.SideEffect
import team.noweekend.core.common.android.mvi.SideEffectHandler

@Composable
internal fun rememberCatalogHomeSideEffectHandler(
    navigateToComponentDetail: (Component) -> Unit,
): CatalogHomeSideEffectHandler {
    return remember {
        CatalogHomeSideEffectHandler(
            navigateToComponentDetail = navigateToComponentDetail,
        )
    }
}

internal class CatalogHomeSideEffectHandler(
    private val navigateToComponentDetail: (Component) -> Unit,
) : SideEffectHandler<SideEffect> {
    override fun handleSideEffect(sideEffect: SideEffect) {
        when (sideEffect) {
            is HomeSideEffect.NavigateToComponentDetail -> navigateToComponentDetail(sideEffect.component)
        }
    }
}
