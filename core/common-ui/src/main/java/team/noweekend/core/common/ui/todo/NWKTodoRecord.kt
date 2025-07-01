package team.noweekend.core.common.ui.todo

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import team.noweekend.core.common.ui.todo.model.Todo
import team.noweekend.core.common.ui.todo.preview.PreviewTodoRecordParameterProvider
import team.noweekend.core.design.system.core.component.checkbox.NWKCheckBox
import team.noweekend.core.design.system.foundation.theme.NWKTheme

@Composable
internal fun NWKTodoRecord(
    todo: Todo,
    onClickOptionButton: () -> Unit,
    modifier: Modifier = Modifier,
    onClickCheckBox: () -> Unit = {},
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        NWKCheckBox(
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

@Preview(showBackground = true)
@Composable
private fun PreviewNWKTodoRecord(
    @PreviewParameter(PreviewTodoRecordParameterProvider::class) todo: Todo,
) {
    NWKTheme {
        NWKTodoRecord(
            todo = todo,
            onClickOptionButton = {},
        )
    }
}
