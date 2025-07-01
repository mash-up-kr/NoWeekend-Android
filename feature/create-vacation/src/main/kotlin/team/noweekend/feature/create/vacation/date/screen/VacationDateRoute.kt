package team.noweekend.feature.create.vacation.date.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest
import team.noweekend.feature.create.vacation.date.mvi.VacationDateIntent
import team.noweekend.feature.create.vacation.date.mvi.VacationDateSideEffectHandler
import team.noweekend.feature.create.vacation.date.mvi.VacationDateUiState
import team.noweekend.feature.create.vacation.date.mvi.VacationDateViewModel
import team.noweekend.feature.create.vacation.date.mvi.rememberVacationDateSideEffectHandler

@Composable
internal fun VacationDateRoute(
    navigateToHistoryBack: () -> Unit,
    navigateToInformation: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: VacationDateViewModel = hiltViewModel(),
) {
    val uiState: VacationDateUiState by viewModel.uiState.collectAsStateWithLifecycle()
    val sideEffectHandler: VacationDateSideEffectHandler = rememberVacationDateSideEffectHandler(
        navigateToHistoryBack = navigateToHistoryBack,
        navigateToInformation = navigateToInformation,
    )

    LaunchedEffect(key1 = Unit) {
        viewModel.sideEffect.collectLatest { sideEffect ->
            sideEffectHandler.handleSideEffect(sideEffect)
        }
    }

    VacationDateScreen(
        modifier = modifier.fillMaxSize(),
        onBackClick = { viewModel.intent(VacationDateIntent.ClickBackButton) },
        onNextClick = { viewModel.intent(VacationDateIntent.ClickNextButton) },
        uiState = uiState,
    )
}
