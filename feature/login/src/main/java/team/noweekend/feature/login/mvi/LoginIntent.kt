package team.noweekend.feature.login.mvi

import android.content.Context
import team.noweekend.core.common.android.mvi.Intent

sealed interface LoginIntent : Intent {
    data class ClickGoogleLogin(val context: Context) : LoginIntent
    data object ClickCancelLogin : LoginIntent
    data class ClickGoogleAuthLogin(val authCode: String?) : LoginIntent
}
