package team.noweekend.feature.create.vacation.information.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest
import team.noweekend.feature.create.vacation.information.mvi.InformationIntent
import team.noweekend.feature.create.vacation.information.mvi.InformationSideEffectHandler
import team.noweekend.feature.create.vacation.information.mvi.InformationViewModel
import team.noweekend.feature.create.vacation.information.mvi.rememberInformationSideEffectHandler

@Composable
internal fun InformationRoute(
    navigateToHistoryBack: () -> Unit,
    navigateToVacationRecommendation: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InformationViewModel = hiltViewModel(),
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val sideEffectHandler: InformationSideEffectHandler = rememberInformationSideEffectHandler(
        navigateToHistoryBack = navigateToHistoryBack,
        navigateToVacationRecommendation = navigateToVacationRecommendation,
    )

    LaunchedEffect(key1 = Unit) {
        viewModel.sideEffect.collectLatest { sideEffect ->
            sideEffectHandler.handleSideEffect(sideEffect)
        }
    }

    InformationScreen(
        uiState = uiState,
        onBackClick = { viewModel.intent(InformationIntent.ClickBackButton) },
        onNextClick = { viewModel.intent(InformationIntent.ClickNextButton) },
        selectInformation = { row, information ->
            viewModel.intent(InformationIntent.SelectInformation(row, information))
        },
    )
}
