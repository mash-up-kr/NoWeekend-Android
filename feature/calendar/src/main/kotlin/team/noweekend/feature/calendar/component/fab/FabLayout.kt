package team.noweekend.feature.calendar.component.fab

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import kotlinx.collections.immutable.ImmutableList
import team.noweekend.core.common.ui.todo.model.Todo
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.feature.calendar.component.fab.core.FabDimAlpha
import team.noweekend.feature.calendar.component.fab.core.FabZIndex
import team.noweekend.feature.calendar.component.fab.core.FabTodoAddButton
import team.noweekend.feature.calendar.component.fab.core.FabTodoItemContainer

@Composable
internal fun FabLayout(
    isExpanded: Boolean,
    todoItemList: ImmutableList<Todo>,
    onClickFabButton: () -> Unit,
    onClickTodo: (index: Int) -> Unit,
    onClickDirectInput: () -> Unit,
    onClickDim: () -> Unit,
    modifier: Modifier = Modifier,
) {

    val dimColor = NWKTheme.color.Neutral.neutralGray700

    if (isExpanded) {
        Canvas(
            modifier = Modifier
                .fillMaxSize().zIndex(FabZIndex)
                .clickable(onClick = onClickDim),
        ) {
            drawRect(
                color = dimColor,
                alpha = FabDimAlpha
            )
        }
    }

    Layout(
        modifier = modifier,
        content = {
            if (isExpanded) {
                FabTodoItemContainer(
                    todoItemList = todoItemList,
                    onClick = onClickTodo,
                    onClickDirectInput = onClickDirectInput,
                )
            }
            FabTodoAddButton(
                isExpanded = isExpanded,
                onClick = onClickFabButton,
            )
        },
    ) { measurables: List<Measurable>, constraints: Constraints ->

        val placeables: List<Placeable> =
            measurables.map { measurable: Measurable -> measurable.measure(constraints) }

        val fabPlaceable = placeables.last()

        val todoContainerPlaceable: Placeable? =
            placeables.takeIf { placeablesList -> placeablesList.size > 1 && isExpanded }?.firstOrNull()

        /**
         * FabButton과 TodoContainer간격
         */
        val itemSpacing: Dp = 12.dp.takeIf { todoContainerPlaceable != null } ?: 0.dp

        /**
         * 최대 너비 계산 (FAB과 FabItem 중 가장 넓은 너비)
         * FabItem이 없는 경우 0
         */
        val maxWidth: Int = placeables.maxOfOrNull { it.width } ?: 0

        /**
         * 전체 높이 계산 FAB 높이 + todoPlaceableHeight + Fab과 TodoContainer간격
         */
        val totalHeight: Int = fabPlaceable.height + (todoContainerPlaceable?.height ?: 0) + itemSpacing.roundToPx()

        layout(maxWidth, totalHeight) {
            val fabY = totalHeight - fabPlaceable.height
            fabPlaceable.placeRelative(
                x = maxWidth - fabPlaceable.width,
                y = fabY,
            )

            val yOffset = fabY - itemSpacing.roundToPx()
            todoContainerPlaceable?.placeRelative(
                x = maxWidth - todoContainerPlaceable.width,
                y = yOffset - todoContainerPlaceable.height,
            )
        }
    }
}


@Preview
@Composable
private fun PreviewFabLayout() {

    NWKTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            var isExpanded by remember { mutableStateOf(false) }
            FabLayout(
                isExpanded = isExpanded,
                todoItemList = Todo.previewDummy,
                onClickFabButton = { isExpanded = isExpanded.not() },
                onClickTodo = { index: Int ->
                    println(Todo.previewDummy[index])
                },
                modifier = Modifier.zIndex(FabZIndex)
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 20.dp, end = 20.dp),
                onClickDirectInput = {},
                onClickDim = { isExpanded = isExpanded.not() },
            )

            Column(
                modifier = Modifier.fillMaxSize().clickable {
                    println("Screen clicked")
                },
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "screen",
                )
            }
        }
    }
}
