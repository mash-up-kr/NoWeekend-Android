package team.noweekend.feature.create.vacation.recommend.mvi

import team.noweekend.core.common.android.mvi.SideEffect

sealed interface RecommendSideEffect : SideEffect {
    data object NavigateToHistoryBack : RecommendSideEffect
}
