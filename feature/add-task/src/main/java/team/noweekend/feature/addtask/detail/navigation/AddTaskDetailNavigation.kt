package team.noweekend.feature.addtask.detail.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import team.noweekend.core.design.system.core.component.toggle.ToggleState
import team.noweekend.core.navigator.model.AddTask
import team.noweekend.feature.addtask.detail.AddTaskDetailRoute
import team.noweekend.feature.addtask.detail.model.VacationTimeType
import team.noweekend.feature.addtask.mvi.AddTaskUiState

internal fun NavHostController.navigateToAddTaskDetail() {
    navigate(AddTask.Detail)
}

internal fun NavGraphBuilder.addTaskDetailNavigation(
    uiState: AddTaskUiState,
    onBackClick: () -> Unit,
    onClickAction: (String) -> Unit,
    onClickInfoSave: () -> Unit,
    onToggleAllDay: (ToggleState) -> Unit,
    onSelectedStartDate: (LocalDate) -> Unit,
    onSelectedStartTime: (LocalTime) -> Unit,
    onSelectedEndDate: (LocalDate) -> Unit,
    onSelectedEndTime: (LocalTime) -> Unit,
    onChangedTemperature: (String) -> Unit,
    onSelectedVacationTime: (VacationTimeType) -> Unit,
) {
    composable<AddTask.Detail> {
        AddTaskDetailRoute(
            uiState = uiState,
            onBackClick = onBackClick,
            onClickAction = onClickAction,
            onClickInfoSave = onClickInfoSave,
            onToggleAllDay = onToggleAllDay,
            onSelectedStartDate = onSelectedStartDate,
            onSelectedStartTime = onSelectedStartTime,
            onSelectedEndDate = onSelectedEndDate,
            onSelectedEndTime = onSelectedEndTime,
            onChangedTemperature = onChangedTemperature,
            onSelectedVacationTime = onSelectedVacationTime,
        )
    }
}
