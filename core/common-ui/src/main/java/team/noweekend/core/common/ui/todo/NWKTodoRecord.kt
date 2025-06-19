package team.noweekend.core.common.ui.todo

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import team.noweekend.core.common.ui.todo.model.Todo
import team.noweekend.core.design.system.core.component.checkbox.NWKCheckBox
import team.noweekend.core.design.system.core.component.checkbox.NWKCheckBoxState
import team.noweekend.core.design.system.foundation.theme.NWKTheme

@Composable
internal fun NWKTodoRecord(
    todo : Todo,
    modifier: Modifier = Modifier,
) {
    val checkBoxState = remember { mutableStateOf(NWKCheckBoxState.UNCHECKED) }
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        NWKCheckBox.Basic(
            checkBoxState = checkBoxState.value,
            onClick = {
                if (checkBoxState.value == NWKCheckBoxState.UNCHECKED) {
                    checkBoxState.value = NWKCheckBoxState.CHECKED
                } else {
                    checkBoxState.value = NWKCheckBoxState.UNCHECKED
                }
            },
        )
        TodoRecordContent(
            modifier = Modifier.weight(1f),
            todo= todo
        )
        TodoRecordOptionButton()
    }
}


@Preview
@Composable
private fun PreviewNWKTodoRecord() {
    NWKTheme {
        NWKTodoRecord()
    }
}
