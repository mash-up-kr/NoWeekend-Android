package team.noweekend.feature.addtask.detail

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import team.noweekend.core.design.system.core.component.toggle.ToggleState
import team.noweekend.feature.addtask.detail.model.VacationTimeType
import team.noweekend.feature.addtask.mvi.AddTaskUiState

@Composable
fun AddTaskDetailRoute(
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
    modifier: Modifier = Modifier,
) {
    AddTaskDetailScreen(
        uiState = uiState,
        modifier = modifier.fillMaxSize(),
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
