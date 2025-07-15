package team.noweekend.feature.profile.mvi

import team.noweekend.core.common.android.mvi.SideEffect

sealed interface ProfileSideEffect : SideEffect {
    data class NavigateToExternalWebBrowser(
        val url: String,
    ) : ProfileSideEffect
}
