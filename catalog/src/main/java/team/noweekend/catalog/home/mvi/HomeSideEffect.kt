package team.noweekend.catalog.home.mvi

import team.noweekend.catalog.model.Component
import team.noweekend.core.common.android.mvi.SideEffect

sealed interface HomeSideEffect : SideEffect {
    data class NavigateToComponentDetail(
        val component: Component,
    ) : HomeSideEffect
}
