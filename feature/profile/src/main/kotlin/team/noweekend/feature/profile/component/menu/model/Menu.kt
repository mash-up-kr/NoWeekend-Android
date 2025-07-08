package team.noweekend.feature.profile.component.menu.model

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import kotlinx.collections.immutable.ImmutableList

sealed interface Menu {
    val title: Int
        @StringRes get
}

