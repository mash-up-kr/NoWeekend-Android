package team.noweekend.core.common.ui.todo.model

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import kotlinx.collections.immutable.toImmutableList
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.core.resource.NWKStringResource

@Immutable
data class Todo(
    val title: String,
    val description: String,
    val todoType: TodoType,
    val isDone: Boolean = false,
) {
    companion object {
        val dummy = (1..100).map {
            Todo(
                title = "title $it",
                description = "description $it",
                todoType = TodoType.Company(),
                isDone = it % 2 == 0,
            )
        }.toImmutableList()
    }
}

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
        override val title: Int = NWKStringResource.TodoCompany,
    ) : TodoType {
        override val color: Color
            @Composable get() = NWKTheme.color.TaskItem.company
    }

    /**
     * 개인
     */
    data class Personal(
        override val title: Int = NWKStringResource.TodoPersonal,
    ) : TodoType {
        override val color: Color
            @Composable get() = NWKTheme.color.TaskItem.personal
    }

    /**
     *  연차
     */
    data class AnnualLeave(
        override val title: Int = NWKStringResource.TodoAnnualLeave,
    ) : TodoType {
        override val color: Color
            @Composable get() = NWKTheme.color.TaskItem.annualLeave
    }

    /**
     * 기타
     */
    data class Etc(
        override val title: Int = NWKStringResource.TodoEtc,
    ) : TodoType {
        override val color: Color
            @Composable get() = NWKTheme.color.TaskItem.etc
    }
}
