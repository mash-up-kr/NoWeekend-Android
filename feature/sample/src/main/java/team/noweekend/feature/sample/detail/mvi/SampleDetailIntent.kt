package team.noweekend.feature.sample.detail.mvi

import team.noweekend.core.common.android.mvi.Intent

sealed interface SampleDetailIntent : Intent {
    data object ClickBackButton : SampleDetailIntent
}