package team.noweekend.catalog.home.mvi

import team.noweekend.catalog.model.Component
import team.noweekend.core.common.android.mvi.Intent

sealed interface HomeIntent : Intent {
    data class ClickComponent(
        val component: Component,
    ) : HomeIntent
}
