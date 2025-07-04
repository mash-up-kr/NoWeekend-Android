package team.noweekend.feature.calendar.component.fab.core

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import team.noweekend.core.common.ui.todo.model.Todo
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.core.resource.NWKStringResource.TodoAddDirect

@Composable
internal fun FabTodoItemContainer(
    todoItemList: ImmutableList<Todo>,
    onClick: (index: Int) -> Unit,
    onClickEdit: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .width(IntrinsicSize.Max)
            .clip(
                shape = NWKTheme.radius.borderRadius400,
            )
            .border(
                width = 1.dp,
                color = NWKTheme.color.Semantic.Border.border01,
            )
            .background(color = NWKTheme.color.Neutral.white)
            .padding(6.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        Column(
            modifier = Modifier.width(IntrinsicSize.Max),
        ) {
            todoItemList.forEachIndexed { index, todo ->
                key(index) {
                    FabTodoItem(
                        todo = todo,
                        onClick = {
                            onClick(index)
                        },
                    )
                }
            }
            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 1.4.dp,
                color = NWKTheme.color.Semantic.Border.border01,
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(44.dp)
                .clickable {
                    onClickEdit()
                }
                .padding(vertical = 11.dp, horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = stringResource(id = TodoAddDirect),
                style = NWKTheme.typography.subTitle1,
                color = NWKTheme.color.Semantic.Text.neutral,
            )
        }
    }
}


@Preview
@Composable
private fun PreviewFabTodoItemContainer() {
    NWKTheme {
        FabTodoItemContainer(
            todoItemList = Todo.previewDummy,
            onClick = {},
            onClickEdit = {},
        )
    }
}
