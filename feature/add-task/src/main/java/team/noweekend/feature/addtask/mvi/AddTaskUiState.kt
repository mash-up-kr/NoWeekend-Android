package team.noweekend.feature.addtask.mvi

import team.noweekend.core.common.android.mvi.UiState
import team.noweekend.feature.addtask.model.AddTaskInfo

data class AddTaskUiState(
    val taskInfo: AddTaskInfo,
    val isLoading: Boolean,
) : UiState {
    companion object {
        val INITIAL_STATE = AddTaskUiState(
            taskInfo = AddTaskInfo.Empty,
            isLoading = false,
        )
    }
}
