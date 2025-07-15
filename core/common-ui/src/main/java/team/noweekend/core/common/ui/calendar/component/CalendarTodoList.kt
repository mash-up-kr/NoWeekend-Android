package team.noweekend.core.common.ui.calendar.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import team.noweekend.core.common.android.extension.fillMaxWidthOfScreen
import team.noweekend.core.common.ui.todo.NWKTodoRecord
import team.noweekend.core.common.ui.todo.model.Todo
import team.noweekend.core.common.ui.todo.model.TodoType
import team.noweekend.core.design.system.foundation.theme.NWKTheme

@Composable
fun CalendarTodoList(
    todoList: ImmutableList<Todo>,
    onClickCheckBox: (Int) -> Unit,
    onClickOptionButton: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val state = rememberLazyListState()

    LazyColumn(modifier = modifier, state = state) {
        stickyHeader {
            CalendarTodoBoardDivider(state = state, modifier = Modifier.fillMaxWidthOfScreen())
        }
        item {
            TodoCalendarBoard(
                modifier = Modifier.fillMaxWidth(),
                todoList = todoList,
            )
        }
        itemsIndexed(todoList) { index, todo ->
            NWKTodoRecord(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                todo = todo,
                onClickOptionButton = { onClickOptionButton(index) },
                onClickCheckBox = { onClickCheckBox(index) },
            )
        }
    }
}

@Preview
@Composable
private fun PreviewCalendarTodoList() {
    NWKTheme {
        CalendarTodoList(
            todoList = persistentListOf(
                Todo(
                    title = "title",
                    description = "description",
                    isDone = false,
                    todoType = TodoType.Etc(),
                ),
                Todo(
                    title = "title",
                    description = "description",
                    isDone = false,
                    todoType = TodoType.Personal(),
                ),
                Todo(
                    title = "title",
                    description = "description",
                    isDone = false,
                    todoType = TodoType.Company(),
                ),
            ),
            onClickCheckBox = {},
            onClickOptionButton = {},
        )
    }
}
