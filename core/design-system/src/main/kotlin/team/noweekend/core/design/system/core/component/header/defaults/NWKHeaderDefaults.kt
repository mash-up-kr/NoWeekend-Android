package team.noweekend.core.design.system.core.component.header.defaults

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import team.noweekend.core.design.system.foundation.theme.NWKTheme

object NWKHeaderDefaults {

    @Composable
    fun getContentPadding(): PaddingValues =
        PaddingValues(
            start = NWKTheme.spacing.space150,
            top = NWKTheme.spacing.space175,
            end = NWKTheme.spacing.space200,
            bottom = NWKTheme.spacing.space175,
        )
}
