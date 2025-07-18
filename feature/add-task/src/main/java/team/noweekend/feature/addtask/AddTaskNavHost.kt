package team.noweekend.feature.addtask

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.flow.collectLatest
import team.noweekend.core.navigator.model.AddTask
import team.noweekend.feature.addtask.detail.navigation.addTaskDetailNavigation
import team.noweekend.feature.addtask.detail.navigation.navigateToAddTaskDetail
import team.noweekend.feature.addtask.input.navigation.addTaskNavigation
import team.noweekend.feature.addtask.mvi.AddTaskIntent
import team.noweekend.feature.addtask.mvi.AddTaskSideEffect
import team.noweekend.feature.addtask.mvi.AddTaskViewModel

@Composable
internal fun AddTaskNavHost(
    navigateCalendar: () -> Unit,
    showSaveSuccessToast: () -> Unit,
    showErrorToast: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AddTaskViewModel = hiltViewModel(),
) {
    val navController: NavHostController = rememberNavController()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collectLatest { sideEffect ->
            when (sideEffect) {
                AddTaskSideEffect.NavigateToBack -> navController.popBackStack()
                AddTaskSideEffect.NavigateToCalendar -> navigateCalendar()
                AddTaskSideEffect.NavigateToDetail -> navController.navigateToAddTaskDetail()
                AddTaskSideEffect.ShowErrorToast -> showErrorToast()
                AddTaskSideEffect.ShowSuccessToast -> showSaveSuccessToast()
            }
        }
    }

    Log.d("AddTaskNavHost", "Current State: $uiState")

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = AddTask.Main,
    ) {
        addTaskNavigation(
            uiState = uiState,
            onClickSave = { viewModel.intent(AddTaskIntent.ClickSave) },
            onClickDetail = { viewModel.intent(AddTaskIntent.ClickDetail) },
            onClickBack = { viewModel.intent(AddTaskIntent.ClickBackMain) },
            onSelectTaskType = { viewModel.intent(AddTaskIntent.SelectTaskType(it)) },
            onChangedText = { viewModel.intent(AddTaskIntent.WriteTitle(it)) },
        )

        addTaskDetailNavigation(
            uiState = uiState,
            onBackClick = { viewModel.intent(AddTaskIntent.ClickBackDetail) },
            onClickAction = { viewModel.intent(AddTaskIntent.WriteTemperature(it)) },
            onClickInfoSave = { viewModel.intent(AddTaskIntent.ClickSave) },
            onToggleAllDay = { viewModel.intent(AddTaskIntent.ToggleAllDay(it)) },
            onSelectedStartDate = { viewModel.intent(AddTaskIntent.SelectStartDate(it)) },
            onSelectedStartTime = { viewModel.intent(AddTaskIntent.SelectStartTime(it)) },
            onSelectedEndDate = { viewModel.intent(AddTaskIntent.SelectEndDate(it)) },
            onSelectedEndTime = { viewModel.intent(AddTaskIntent.SelectEndTime(it)) },
            onChangedTemperature = { viewModel.intent(AddTaskIntent.WriteTemperature(it)) },
            onSelectedVacationTime = { viewModel.intent(AddTaskIntent.SelectVacationTime(it)) },
        )
    }
}
