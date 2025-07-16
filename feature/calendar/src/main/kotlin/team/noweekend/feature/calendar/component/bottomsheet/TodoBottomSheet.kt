package team.noweekend.feature.calendar.component.bottomsheet

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import team.noweekend.core.common.ui.todo.model.Todo
import team.noweekend.core.common.ui.todo.model.TodoType
import team.noweekend.core.design.system.core.component.bottomSheet.BottomSheetType
import team.noweekend.core.design.system.core.component.bottomSheet.NWKBottomSheet
import team.noweekend.core.design.system.core.component.bottomSheet.rememberNWKBottomSheetState
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.feature.calendar.model.TodoRecordAction

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoBottomSheet(
    todo: Todo,
    onDismissRequest: () -> Unit,
    onClickAction: (TodoRecordAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    val nwkBottomSheetState = rememberNWKBottomSheetState(
        bottomSheetType = BottomSheetType.OnlyContents,
    )

    NWKBottomSheet(
        modifier = modifier,
        nwkBottomSheetState = nwkBottomSheetState,
        shouldDismissOnBackPress = true,
        onDismissRequest = onDismissRequest,
    ) {
        when (todo.todoType) {
            is TodoType.AnnualLeave -> {
                TodoRecordAction.filteredActionList.forEach { action ->
                    key(action) {
                        TodoRecordActionComponent(
                            todoRecordAction = action,
                            onClickAction = onClickAction,
                        )
                    }
                }
            }

            else -> {
                TodoRecordAction.actionList.forEach { action ->
                    key(action) {
                        TodoRecordActionComponent(
                            todoRecordAction = action,
                            onClickAction = onClickAction,
                        )
                    }
                }
            }
        }
    }
}


@Preview
@Composable
private fun PreviewTodoBottomSheet() {
    NWKTheme {
        TodoBottomSheet(
            todo = Todo(
                title = "",
                description = "",
                todoType = TodoType.Etc(),
                isDone = false,
                id = "0"
            ),
            onDismissRequest = {},
            onClickAction = {},
        )
    }
}


