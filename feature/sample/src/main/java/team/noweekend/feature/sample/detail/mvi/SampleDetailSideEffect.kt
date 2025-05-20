package team.noweekend.feature.sample.detail.mvi

import team.noweekend.core.common.android.mvi.SideEffect

sealed interface SampleDetailSideEffect : SideEffect {
    data object NavigateToHistoryBack : SampleDetailSideEffect
}