package team.noweekend.feature.detail.date.mvi

import androidx.compose.runtime.Immutable
import kotlinx.collections.immutable.ImmutableList
import kotlinx.datetime.LocalDate
import team.noweekend.core.common.android.mvi.UiState
import team.noweekend.core.common.ui.todo.model.Todo
import team.noweekend.core.common.ui.todo.model.TodoType
import team.noweekend.feature.detail.date.model.DegreeUIModel

@Immutable
data class DetailDateUiState(
    val dateTitle: String,
    val date: LocalDate,
    val todoList: ImmutableList<Todo>,
    val recommendTodoTagList: ImmutableList<Todo>,
    val degreeUiModel: DegreeUIModel,
    val todoOptionVisibility: TodoOptionVisibility = TodoOptionVisibility(visible = false),
) : UiState

@Immutable
data class TodoOptionVisibility(
    val visible: Boolean,
    val todoIndex: Int = 0,
    val todoType: TodoType = TodoType.Personal(),
)
