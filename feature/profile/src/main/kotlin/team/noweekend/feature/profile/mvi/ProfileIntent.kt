package team.noweekend.feature.profile.mvi

import team.noweekend.core.common.android.mvi.Intent
import team.noweekend.feature.profile.model.Menu

sealed interface ProfileIntent : Intent {
    data class ClickMenu(
        val menu: Menu,
    ) : ProfileIntent
}
