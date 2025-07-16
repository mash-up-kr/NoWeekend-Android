package team.noweekend.feature.detail.date.mvi

import team.noweekend.core.common.android.mvi.Intent

sealed interface DetailDateIntent : Intent {
    data object InitState : DetailDateIntent

    data class ChangeComplete(val index: Int) : DetailDateIntent

    data object GetRecommendTodoTagList : DetailDateIntent

    data class ClickRecommendTodoTag(val index: Int) : DetailDateIntent

    data object ClickBackButton : DetailDateIntent
}
