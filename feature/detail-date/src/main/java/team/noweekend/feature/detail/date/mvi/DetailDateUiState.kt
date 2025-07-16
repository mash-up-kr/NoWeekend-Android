package team.noweekend.feature.detail.date.mvi

import androidx.compose.runtime.Immutable
import kotlinx.collections.immutable.ImmutableList
import kotlinx.datetime.LocalDate
import team.noweekend.core.common.android.mvi.UiState
import team.noweekend.core.common.ui.todo.model.Todo
import team.noweekend.feature.detail.date.model.DegreeUIModel

@Immutable
data class DetailDateUiState(
    val dateTitle: String,
    val date: LocalDate,
    val todoList: ImmutableList<Todo>,
    val recommendTodoTagList : ImmutableList<Todo>,
    val degreeUiModel: DegreeUIModel,
) : UiState
