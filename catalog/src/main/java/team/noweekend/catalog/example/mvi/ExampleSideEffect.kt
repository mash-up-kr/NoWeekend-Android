package team.noweekend.catalog.example.mvi

import team.noweekend.core.common.android.mvi.SideEffect

interface ExampleSideEffect : SideEffect {
    data object NavigateToHistoryBack : ExampleSideEffect
}

