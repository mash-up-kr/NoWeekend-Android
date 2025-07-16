package team.noweekend.core.design.system.foundation.color.semantics

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import team.noweekend.core.design.system.foundation.color.semantics.background.BackgroundColor
import team.noweekend.core.design.system.foundation.color.semantics.background.LocalBackgroundColor
import team.noweekend.core.design.system.foundation.color.semantics.border.BorderColor
import team.noweekend.core.design.system.foundation.color.semantics.border.LocalBorderColor
import team.noweekend.core.design.system.foundation.color.semantics.text.LocalTextColor
import team.noweekend.core.design.system.foundation.color.semantics.text.TextColor

internal val LocalSemanticTheme: ProvidableCompositionLocal<SemanticTheme> =
    staticCompositionLocalOf { SemanticTheme }

object SemanticTheme {
    val Background: BackgroundColor
        @Composable
        @ReadOnlyComposable
        get() = LocalBackgroundColor.current

    val Border: BorderColor
        @Composable
        @ReadOnlyComposable
        get() = LocalBorderColor.current

    val Text: TextColor
        @Composable
        @ReadOnlyComposable
        get() = LocalTextColor.current
}
