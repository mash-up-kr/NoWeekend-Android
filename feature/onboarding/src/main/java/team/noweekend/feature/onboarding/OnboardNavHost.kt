package team.noweekend.feature.onboarding

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
import team.noweekend.core.navigator.model.Onboard
import team.noweekend.feature.onboarding.mvi.OnboardIntent
import team.noweekend.feature.onboarding.mvi.OnboardSideEffect
import team.noweekend.feature.onboarding.mvi.OnboardViewModel
import team.noweekend.feature.onboarding.profile.navigation.profileGraph
import team.noweekend.feature.onboarding.schedule.navigation.navigateToSchedule
import team.noweekend.feature.onboarding.schedule.navigation.scheduleGraph
import team.noweekend.feature.onboarding.vacation.navigation.navigateToRemainedVacation
import team.noweekend.feature.onboarding.vacation.navigation.remainedVacationGraph

@Composable
internal fun OnboardNavHost(
    navigateToHome: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: OnboardViewModel = hiltViewModel(),
) {
    val navController: NavHostController = rememberNavController()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Log.d("OnboardNavHost", "Current UI State: $uiState")

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collectLatest { sideEffect ->
            when (sideEffect) {
                OnboardSideEffect.NavigateToBack -> navController.popBackStack()
                OnboardSideEffect.NavigateToHome -> navigateToHome()
                OnboardSideEffect.NavigateToScheduleTag -> navController.navigateToSchedule()
                OnboardSideEffect.NavigateToVacation -> navController.navigateToRemainedVacation()
            }
        }
    }

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Onboard.Profile,
    ) {
        profileGraph(
            uiState = uiState,
            onBackClick = { viewModel.intent(OnboardIntent.ClickBack) },
            onConfirmClick = { nickname, birth ->
                viewModel.intent(OnboardIntent.ClickProfileNext(birth = birth, nickname = nickname))
            },
        )
        remainedVacationGraph(
            uiState = uiState,
            onBackClick = { viewModel.intent(OnboardIntent.ClickBack) },
            onConfirmClick = { vacationDay ->
                viewModel.intent(OnboardIntent.ClickVacationNext(vacationDay = vacationDay))
            },
            onHalfVacationClick = { viewModel.intent(OnboardIntent.ClickHalfVacation(it)) },
        )
        scheduleGraph(
            uiState = uiState,
            onBackClick = { viewModel.intent(OnboardIntent.ClickBack) },
            onConfirmClick = { viewModel.intent(OnboardIntent.ClickFinishOnboarding(it)) },
            onSelectedChip = { viewModel.intent(OnboardIntent.SelectTag(it)) },
        )
    }
}
