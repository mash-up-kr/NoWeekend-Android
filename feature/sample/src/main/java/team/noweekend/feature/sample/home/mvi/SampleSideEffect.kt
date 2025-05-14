package team.noweekend.feature.sample.home.mvi

import team.noweekend.core.common.android.mvi.SideEffect

sealed interface SampleSideEffect : SideEffect {
    data class NavigateToMemberDetail(
        val members: List<String>,
    ) : SampleSideEffect
}