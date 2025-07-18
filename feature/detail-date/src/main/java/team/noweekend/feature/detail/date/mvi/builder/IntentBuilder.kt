package team.noweekend.feature.detail.date.mvi.builder

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import team.noweekend.core.common.ui.todo.model.TodoRecordAction
import team.noweekend.feature.detail.date.mvi.DetailDateIntent

@Composable
fun rememberIntentBuilder(
    send: (DetailDateIntent) -> Unit,
) = remember {
    IntentBuilder(send = send)
}

@Stable
class IntentBuilder(
    private val send: (DetailDateIntent) -> Unit,
) {

    private fun build(detailDateIntent: DetailDateIntent) {
        send(detailDateIntent)
    }

    fun changeCompleteSchedule(index: Int) {
        build(DetailDateIntent.ChangeComplete(index = index))
    }

    fun getInitState() {
        build(DetailDateIntent.InitState)
    }

    fun clickRecommendTodoTag(index: Int) {
        build(DetailDateIntent.ClickRecommendTodoTag(index = index))
    }

    fun getRecommendTodoTagList() {
        build(DetailDateIntent.GetRecommendTodoTagList)
    }

    fun clickBackButton() {
        build(DetailDateIntent.ClickBackButton)
    }

    fun clickDirectInput() {
        build(DetailDateIntent.ClickDirectInput)
    }

    fun clickTodoOption(index: Int) {
        build(DetailDateIntent.ClickTodoOption(index = index))
    }

    fun clickAction(todoRecordAction: TodoRecordAction, todoIndex: Int) {
        when (todoRecordAction) {
            is TodoRecordAction.AddSameAction -> {
                build(DetailDateIntent.AddSameTodo(index = todoIndex))
            }
            is TodoRecordAction.DeleteAction -> {
                build(DetailDateIntent.DeleteTodo(index = todoIndex))
            }
            is TodoRecordAction.EditAction -> {
                build(DetailDateIntent.EditTodo(index = todoIndex))
            }
        }
    }

    fun dismissTodoOption() {
        build(DetailDateIntent.DismissTodo)
    }
}
