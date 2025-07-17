package team.noweekend.feature.addtask.input.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import team.noweekend.core.model.schedule.ScheduleCategory
import team.noweekend.core.navigator.model.AddTask
import team.noweekend.feature.addtask.input.screen.AddTaskRoute
import team.noweekend.feature.addtask.mvi.AddTaskUiState

internal fun NavHostController.navigateToAddTask() {
    navigate(AddTask.Main)
}

internal fun NavGraphBuilder.addTaskNavigation(
    uiState: AddTaskUiState,
    onClickSave: () -> Unit,
    onClickDetail: () -> Unit,
    onClickBack: () -> Unit,
    onSelectTaskType: (ScheduleCategory) -> Unit,
    onChangedText: (String) -> Unit,
) {
    composable<AddTask.Main> {
        AddTaskRoute(
            uiState = uiState,
            onClickSave = onClickSave,
            onClickDetail = onClickDetail,
            onSelectTaskType = onSelectTaskType,
            onClickBack = onClickBack,
            onChangedText = onChangedText,
        )
    }
}
