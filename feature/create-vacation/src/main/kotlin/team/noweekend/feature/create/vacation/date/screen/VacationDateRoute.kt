package team.noweekend.feature.create.vacation.date.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import team.noweekend.feature.create.vacation.date.mvi.VacationDateUiState
import team.noweekend.feature.create.vacation.date.mvi.VacationDateViewModel

@Composable
internal fun VacationDateRoute(
    modifier: Modifier = Modifier,
    viewModel: VacationDateViewModel = hiltViewModel(),
) {
    val uiState: VacationDateUiState by viewModel.uiState.collectAsStateWithLifecycle()

    VacationDateScreen(
        modifier = modifier.fillMaxSize(),
        onNextClick = {},
        uiState = uiState,
    )
}
