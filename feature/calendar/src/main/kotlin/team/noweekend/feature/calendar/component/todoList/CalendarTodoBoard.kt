package team.noweekend.feature.calendar.component.todoList

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import team.noweekend.core.common.ui.todo.model.Todo
import team.noweekend.core.common.ui.todo.model.TodoType
import team.noweekend.core.design.system.foundation.theme.NWKTheme

@Composable
fun TodoCalendarBoard(
    todoList: ImmutableList<Todo>,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(54.dp)
            .padding(top = 20.dp),
    ) {
        Text(
            text = finishedBoard(todoList),
            style = NWKTheme.typography.heading5,
        )
    }
}


@Composable
private fun finishedBoard(
    todoList: ImmutableList<Todo>,
): AnnotatedString {
    val isDoneCount = todoList.count { it.isDone }
    val totalCount = todoList.size
    return buildAnnotatedString {

        append("할 일 $isDoneCount")
        withStyle(
            style = SpanStyle(
                color = NWKTheme.color.Neutral.neutralGray700,
            ),
        ) {
            append(" / ")
        }
        withStyle(
            style = SpanStyle(
                color = NWKTheme.color.Semantic.Text.disabled,
            ),
        ) {
            append("$totalCount ")
        }
        append("개")
    }
}


@Preview
@Composable
private fun PreviewTodoCalendarBoard() {
    NWKTheme {
        TodoCalendarBoard(
            todoList = persistentListOf(
                Todo(
                    title = "title",
                    description = "description",
                    isDone = true,
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
                    isDone = true,
                    todoType = TodoType.Company(),
                ),
            ),
        )
    }
}
