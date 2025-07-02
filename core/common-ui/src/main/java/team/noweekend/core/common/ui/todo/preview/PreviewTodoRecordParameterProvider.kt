package team.noweekend.core.common.ui.todo.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import team.noweekend.core.common.ui.todo.model.Todo
import team.noweekend.core.common.ui.todo.model.TodoType

internal class PreviewTodoRecordParameterProvider : PreviewParameterProvider<Todo> {
    override val count: Int
        get() = super.count
    override val values: Sequence<Todo>
        get() = sequenceOf(
            Todo(
                title = "출근하기",
                description = "오전 9시 30분까지 출근하기",
                isDone = true,
                todoType = TodoType.Company(),
            ),
            Todo(
                title = "친구 약속",
                description = "밥먹기",
                isDone = true,
                todoType = TodoType.Personal(),
            ),
            Todo(
                title = "여행",
                description = "제주도 여행",
                isDone = false,
                todoType = TodoType.AnnualLeave(),
            ),
            Todo(
                title = "집콕",
                description = "집에서 쉬기",
                isDone = false,
                todoType = TodoType.Etc(),
            ),
        )
}
