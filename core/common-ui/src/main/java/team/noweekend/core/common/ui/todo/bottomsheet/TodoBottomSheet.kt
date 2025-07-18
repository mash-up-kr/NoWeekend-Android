package team.noweekend.core.common.ui.todo.bottomsheet

import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import team.noweekend.core.common.ui.todo.model.TodoRecordAction
import team.noweekend.core.common.ui.todo.model.TodoType
import team.noweekend.core.design.system.core.component.bottomSheet.BottomSheetType
import team.noweekend.core.design.system.core.component.bottomSheet.NWKBottomSheet
import team.noweekend.core.design.system.core.component.bottomSheet.rememberNWKBottomSheetState
import team.noweekend.core.design.system.foundation.theme.NWKTheme

@Composable
fun TodoBottomSheet(
    todoIndex: Int,
    todoType: TodoType,
    onDismissRequest: () -> Unit,
    onClickAction: (TodoRecordAction, todoIndex: Int) -> Unit,
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
        when (todoType) {
            is TodoType.AnnualLeave -> {
                TodoRecordAction.filteredActionList.forEach { action ->
                    key(action) {
                        TodoRecordActionComponent(
                            todoRecordAction = action,
                            onClickAction = { recordAction ->
                                onClickAction(recordAction, todoIndex)
                            },
                        )
                    }
                }
            }

            else -> {
                TodoRecordAction.actionList.forEach { action ->
                    key(action) {
                        TodoRecordActionComponent(
                            todoRecordAction = action,
                            onClickAction = { recordAction ->
                                onClickAction(recordAction, todoIndex)
                            },
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
            todoType = TodoType.Etc(),
            onDismissRequest = {},
            onClickAction = { _, _ -> },
            todoIndex = 0,

            )
    }
}


