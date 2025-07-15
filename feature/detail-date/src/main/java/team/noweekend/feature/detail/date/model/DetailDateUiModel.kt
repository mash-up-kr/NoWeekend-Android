package team.noweekend.feature.detail.date.model

import kotlinx.collections.immutable.ImmutableList
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import team.noweekend.core.common.ui.todo.model.Todo

@Serializable
data class DetailDateUiModel(
    val dateTitle: String,
    val degreeUIModel: DegreeUIModel,
    val todoList: ImmutableList<@Contextual Todo>,
)
