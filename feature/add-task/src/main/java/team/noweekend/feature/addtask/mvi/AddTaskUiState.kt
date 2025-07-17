package team.noweekend.feature.addtask.mvi

import team.noweekend.core.common.android.mvi.UiState
import team.noweekend.core.model.schedule.ScheduleCategory
import team.noweekend.feature.addtask.model.AddTaskInfo

data class AddTaskUiState(
    val taskInfo: AddTaskInfo,
    val selectedType: ScheduleCategory,
    val isLoading: Boolean,
) : UiState {
    companion object {
        val INITIAL_STATE = AddTaskUiState(
            taskInfo = AddTaskInfo.Empty,
            isLoading = false,
            selectedType = ScheduleCategory.COMPANY,
        )
    }
}
