package team.noweekend.core.design.system.foundation.theme

import androidx.compose.foundation.LocalIndication
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import team.noweekend.core.design.system.foundation.theme.ripple.NWKRippleConfiguration

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoWeekendTheme(
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalIndication provides ripple(),
        LocalRippleConfiguration provides NWKRippleConfiguration,
    ) {
        content()
    }
}
