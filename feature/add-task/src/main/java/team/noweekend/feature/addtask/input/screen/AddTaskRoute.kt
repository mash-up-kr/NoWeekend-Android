package team.noweekend.feature.addtask.input.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import team.noweekend.core.model.schedule.ScheduleCategory
import team.noweekend.feature.addtask.mvi.AddTaskUiState

@Composable
internal fun AddTaskRoute(
    uiState: AddTaskUiState,
    onClickSave: () -> Unit,
    onClickDetail: () -> Unit,
    onClickBack: () -> Unit,
    onSelectTaskType: (ScheduleCategory) -> Unit,
    onChangedText: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    AddTaskScreen(
        modifier = modifier.fillMaxSize(),
        uiState = uiState,
        onClickSave = onClickSave,
        onClickDetail = onClickDetail,
        onSelectTaskType = onSelectTaskType,
        onClickBack = onClickBack,
        onChangedText = onChangedText,
    )
}
