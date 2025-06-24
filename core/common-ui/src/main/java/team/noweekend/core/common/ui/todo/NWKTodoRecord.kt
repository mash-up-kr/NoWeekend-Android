package team.noweekend.core.common.ui.todo

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import team.noweekend.core.common.ui.todo.model.Todo
import team.noweekend.core.common.ui.todo.model.TodoType
import team.noweekend.core.design.system.core.component.checkbox.NWKCheckBox
import team.noweekend.core.design.system.foundation.theme.NWKTheme

@Composable
internal fun NWKTodoRecord(
    todo: Todo,
    modifier: Modifier = Modifier,
    onClickCheckBox: () -> Unit = {},
    onClickOptionButton: () -> Unit = {},
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        NWKCheckBox.Basic(
            isChecked = todo.isDone,
            onClick = onClickCheckBox,
        )
        TodoRecordContent(
            modifier = Modifier.weight(1f),
            todo = todo,
        )
        TodoRecordOptionButton(
            onClickOptionButton = onClickOptionButton,
        )
    }
}

@Preview
@Composable
private fun PreviewNWKTodoRecord() {
    NWKTheme {
        var todo by remember {
            mutableStateOf(
                Todo(
                    title = "코틀린 공부하기",
                    description = "코틀린 공부하기",
                    todoType = TodoType.Personal(),
                ),
            )
        }
        NWKTodoRecord(
            todo = todo,
            onClickCheckBox = {
                todo = todo.copy(isDone = todo.isDone.not())
            },
        )
    }
}
