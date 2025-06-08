package team.noweekend.feature.login.mvi

import team.noweekend.core.common.android.mvi.Intent

sealed interface LoginIntent : Intent {
    data object ClickGoogleLogin : LoginIntent
}
