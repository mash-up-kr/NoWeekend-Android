package team.noweekend.feature.detail.date.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import team.noweekend.feature.detail.date.model.DegreeUIModel
import team.noweekend.feature.detail.date.model.DetailDateUiModel
import kotlinx.collections.immutable.persistentListOf
import team.noweekend.feature.detail.date.mvi.DetailDateViewModel

@Composable
internal fun DetailDateRoute(
    onClickBackButton: () -> Unit,
    modifier: Modifier = Modifier,
    detailDateViewModel: DetailDateViewModel = hiltViewModel()
) {

    val state = detailDateViewModel.uiState.collectAsStateWithLifecycle()

    DetailDateScreen(
        modifier = modifier,
        detailDateUiState = state.value,
        onClickCheckBox = {},
        onClickOptionButton = {},
        onClickBackButton = onClickBackButton,
    )
}
