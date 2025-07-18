package team.noweekend.feature.home.screen

import android.app.Activity
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest
import team.noweekend.core.navigator.extra.CREATE_VACATION_ACTIVITY_TYPE
import team.noweekend.core.navigator.extra.CREATE_VACATION_DAYS
import team.noweekend.core.navigator.extra.CREATE_VACATION_LEISURE_PREFERENCE
import team.noweekend.core.navigator.extra.CREATE_VACATION_REST_PREFERENCE
import team.noweekend.core.navigator.extra.CREATE_VACATION_TRAVEL_STYLE
import team.noweekend.feature.home.component.common.bottomsheet.TaskTitleBottomSheet
import team.noweekend.feature.home.mvi.HomeIntent
import team.noweekend.feature.home.mvi.HomeSideEffectHandler
import team.noweekend.feature.home.mvi.HomeUiState
import team.noweekend.feature.home.mvi.HomeViewModel
import team.noweekend.feature.home.mvi.rememberHomeSideEffectHandler

@Composable
internal fun HomeRoute(
    navigateToCreateVacation: ((Intent.() -> Intent)?, ActivityResultLauncher<Intent>?) -> Unit,
    navigateToCalendar: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val uiState: HomeUiState by viewModel.uiState.collectAsStateWithLifecycle()

    val createVacationLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.StartActivityForResult(),
            onResult = { result ->
                val resultCode = result.resultCode
                val data = result.data
                if (resultCode == Activity.RESULT_OK && data != null) {
                    val days = data.getIntExtra(CREATE_VACATION_DAYS, 0)
                    val travelStyle = data.getStringExtra(CREATE_VACATION_TRAVEL_STYLE).orEmpty()
                    val activityType = data.getStringExtra(CREATE_VACATION_ACTIVITY_TYPE).orEmpty()
                    val restPreference = data.getStringExtra(CREATE_VACATION_REST_PREFERENCE).orEmpty()
                    val leisurePreference = data.getStringExtra(CREATE_VACATION_LEISURE_PREFERENCE).orEmpty()

                    viewModel.intent(
                        HomeIntent.CreateVacation(
                            days = days,
                            travelStyle = travelStyle,
                            activityType = activityType,
                            restPreference = restPreference,
                            leisurePreference = leisurePreference,
                        ),
                    )
                }
            },
        )

    val sideEffectHandler: HomeSideEffectHandler = rememberHomeSideEffectHandler(
        navigateToCreateVacation = { intentBuilder, _ ->
            navigateToCreateVacation(intentBuilder, createVacationLauncher)
        },
        navigateToCalendar = navigateToCalendar,
    )

    LaunchedEffect(key1 = Unit) {
        viewModel.sideEffect.collectLatest { sideEffectHandler.handleSideEffect(it) }
    }

    LifecycleEventEffect(Lifecycle.Event.ON_START) {
        viewModel.getCalendarData()
    }

    HomeScreen(
        uiState = uiState,
        onCreateVacationClick = { viewModel.intent(HomeIntent.ClickCreateVacation) },
        onHolidayVacationClick = { viewModel.intent(HomeIntent.ClickHolidayVacationCard(it)) },
        onRecommendedVacationClick = { viewModel.intent(HomeIntent.ClickRecommendationVacationCard(it)) },
        onPopularVacationClick = { viewModel.intent(HomeIntent.ClickPopularVacation(it)) },
        modifier = modifier,
    )

    if (uiState.taskTitleBottomSheetState.showTaskTitleBottomSheet) {
        TaskTitleBottomSheet(
            onAddTaskClick = { viewModel.intent(HomeIntent.BottomSheet.ClickAddTaskButton(it)) },
            onBottomSheetDismiss = { viewModel.intent(HomeIntent.BottomSheet.DismissTaskTitleBottomSheet) },
        )
    }
}
