package team.noweekend.feature.create.vacation.recommend.mvi

import team.noweekend.core.common.android.mvi.Intent

sealed interface RecommendIntent : Intent {
    data object ClickBackButton : RecommendIntent
}
