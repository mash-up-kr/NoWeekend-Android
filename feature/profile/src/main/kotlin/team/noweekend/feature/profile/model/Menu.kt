package team.noweekend.feature.profile.model

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable

@Stable
interface Menu {
    val title: Int
        @StringRes get
    val content: @Composable (() -> Unit)?
        get() = null

}

