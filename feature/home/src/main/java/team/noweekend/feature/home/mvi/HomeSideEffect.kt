package team.noweekend.feature.home.mvi

import android.content.Intent
import team.noweekend.core.common.android.mvi.SideEffect

sealed interface HomeSideEffect : SideEffect {
    data class NavigateToCreateVacation(
        val intentBuilder: (Intent.() -> Intent)?,
    ) : HomeSideEffect
}
