package team.noweekend.core.design.system.foundation.color

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import team.noweekend.core.design.system.foundation.color.neutral.LocalNeutralColor
import team.noweekend.core.design.system.foundation.color.neutral.NeutralColor
import team.noweekend.core.design.system.foundation.color.semantics.LocalSemanticTheme
import team.noweekend.core.design.system.foundation.color.semantics.SemanticTheme
import team.noweekend.core.design.system.foundation.color.taskItem.LocalTaskItemColor
import team.noweekend.core.design.system.foundation.color.taskItem.TaskItemColor
import team.noweekend.core.design.system.foundation.color.toast.LocalToastColor
import team.noweekend.core.design.system.foundation.color.toast.ToastColor

internal val LocalColor: ProvidableCompositionLocal<ColorTheme> =
    staticCompositionLocalOf { ColorTheme }

object ColorTheme {
    val Neutral: NeutralColor
        @Composable
        @ReadOnlyComposable
        get() = LocalNeutralColor.current

    val Toast: ToastColor
        @Composable
        @ReadOnlyComposable
        get() = LocalToastColor.current

    val Semantic: SemanticTheme
        @Composable
        @ReadOnlyComposable
        get() = LocalSemanticTheme.current

    val TaskItem: TaskItemColor
        @Composable
        @ReadOnlyComposable
        get() = LocalTaskItemColor.current
}
