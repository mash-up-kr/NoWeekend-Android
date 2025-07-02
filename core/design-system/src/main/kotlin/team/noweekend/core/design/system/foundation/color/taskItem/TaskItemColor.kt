package team.noweekend.core.design.system.foundation.color.taskItem

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import team.noweekend.core.design.system.foundation.color.token.NeutralColorToken
import team.noweekend.core.design.system.foundation.color.token.ToastColorToken

internal val LocalTaskItemColor: ProvidableCompositionLocal<TaskItemColor> =
    staticCompositionLocalOf {
        TaskItemColor(
            company = TaskItemColorToken.Company,
            personal = ToastColorToken.Toast500,
            annualLeave = TaskItemColorToken.AnnualLeave,
            etc = NeutralColorToken.NeutralGray900,
        )
    }

data class TaskItemColor(
    val company: Color,
    val personal: Color,
    val annualLeave: Color,
    val etc: Color,
)
