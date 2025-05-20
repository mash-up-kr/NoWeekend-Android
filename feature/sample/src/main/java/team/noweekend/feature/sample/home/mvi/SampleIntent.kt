package team.noweekend.feature.sample.home.mvi

import team.noweekend.core.common.android.mvi.Intent

sealed interface SampleIntent : Intent {
    data object ClickBackButton : SampleIntent
    data object ClickMemberDetailButton : SampleIntent
}