package team.noweekend.core.common.ui.fab.core

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.noweekend.core.common.ui.todo.model.Todo
import team.noweekend.core.design.system.foundation.theme.NWKTheme

@Composable
internal fun FabTodoItem(
    todo: Todo,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val todoColor: Color = todo.todoType.color
    Row(
        modifier = modifier
            .width(146.dp)
            .height(44.dp)
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Canvas(
            modifier = Modifier
                .padding(start = 14.dp)
                .size(12.dp),
        ) {
            drawCircle(color = todoColor)
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(end = 12.dp),
            text = todo.title,
            style = NWKTheme.typography.subTitle1,
            color = NWKTheme.color.Semantic.Text.neutral,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewFabTodoItem() {
    NWKTheme {
        FabTodoItem(
            todo = Todo.previewDummy.first(),
            onClick = {},
        )
    }
}
