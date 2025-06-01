package team.noweekend.catalog.component.mvi

import team.noweekend.core.common.android.mvi.Intent

sealed interface ComponentIntent : Intent {
    data object ClickBackButton : ComponentIntent
}
