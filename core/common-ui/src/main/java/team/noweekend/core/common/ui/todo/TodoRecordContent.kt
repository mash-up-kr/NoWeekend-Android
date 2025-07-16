package team.noweekend.core.common.ui.todo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.noweekend.core.common.ui.todo.model.Todo
import team.noweekend.core.common.ui.todo.model.TodoType
import team.noweekend.core.design.system.foundation.theme.NWKTheme

@Composable
fun TodoRecordContent(
    todo: Todo,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .graphicsLayer {
                if (todo.isDone) {
                    alpha = 0.5f
                } else {
                    alpha = 1f
                }
            },
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = todo.title,
            style = NWKTheme.typography.body2,
            color = NWKTheme.color.Semantic.Text.neutral,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = stringResource(id = todo.todoType.title),
                style = NWKTheme.typography.body2,
                color = todo.todoType.color,
            )
            VerticalDivider(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(vertical = 5.dp),
                color = NWKTheme.color.Semantic.Border.border02,
                thickness = 1.dp,
            )
            Text(
                text = todo.description,
                style = NWKTheme.typography.body2,
                color = NWKTheme.color.Semantic.Text.body,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewTodoRecordContent() {
    NWKTheme {
        TodoRecordContent(
            todo = Todo(
                title = "회의 참석",
                description = "10:00 ~ 11:00",
                todoType = TodoType.Company(),
                id = "0"
            ),
        )
    }
}
