package team.noweekend.core.common.ui.todo.model

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.core.resource.R

@Immutable
data class Todo(
    val title: String,
    val description: String,
    val todoType: TodoType,
    val isDone: Boolean = false,
)

@Stable
sealed interface TodoType {
    val title: Int
        @StringRes get
    val color: Color
        @Composable get

    /**
     * 회사
     */
    data class Company(
        override val title: Int = R.string.company,
    ) : TodoType {
        override val color: Color
            @Composable get() = NWKTheme.color.TaskItem.company
    }

    /**
     * 개인
     */
    data class Personal(
        override val title: Int = R.string.personal,
    ) : TodoType {
        override val color: Color
            @Composable get() = NWKTheme.color.TaskItem.personal
    }

    /**
     *  연차
     */
    data class AnnualLeave(
        override val title: Int = R.string.annual_leave,
    ) : TodoType {
        override val color: Color
            @Composable get() = NWKTheme.color.TaskItem.annualLeave
    }

    /**
     * 기타
     */
    data class Etc(
        override val title: Int = R.string.etc,
    ) : TodoType {
        override val color: Color
            @Composable get() = NWKTheme.color.TaskItem.etc
    }
}
