package team.noweekend.core.common.ui.todo.model

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.core.resource.R

@Stable
data class Todo(
    val title: String,
    val description: String,
    val todoType: TodoType,
)

@Stable
sealed interface TodoType {
    val title: Int @StringRes get
    val color: Color @Composable get

    data class Company(
        override val title: Int = R.string.company,
        override val color: Color = Color(0xFF0F816F),
    ) : TodoType

    data class Personal(
        override val title: Int = R.string.personal,
    ) : TodoType {
        override val color: Color
            @Composable get() = NWKTheme.color.Toast.toast500
    }

    data class AnnualLeave(
        override val title: Int = R.string.annual_leave,
        override val color: Color = Color(0xFF801BC3),
    ) : TodoType

    data class Etc(
        override val title: Int = R.string.etc,
    ) : TodoType {
        override val color: Color
            @Composable get() = NWKTheme.color.Neutral.neutralGray800
    }
}
