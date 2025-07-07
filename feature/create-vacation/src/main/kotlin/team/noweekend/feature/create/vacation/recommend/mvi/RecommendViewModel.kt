package team.noweekend.feature.create.vacation.recommend.mvi

import androidx.compose.runtime.Composable
import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import team.noweekend.core.common.android.base.MVIViewModel
import javax.inject.Inject

@HiltViewModel
class RecommendViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : MVIViewModel<RecommendIntent, RecommendSideEffect, RecommendUiState>(
    savedStateHandle = savedStateHandle,
) {
    init {
        loading()
    }
    override fun createInitialState(savedStateHandle: SavedStateHandle): RecommendUiState {
        return RecommendUiState.INITIAL_STATE
    }

    override fun handleClientException(throwable: Throwable) {

    }

    override suspend fun handleIntent(intent: RecommendIntent) {
        when (intent) {
            RecommendIntent.ClickBackButton -> navigateToHistoryBack()
        }
    }

    private fun navigateToHistoryBack() = execute {
        postSideEffect(RecommendSideEffect.NavigateToHistoryBack)
    }

    private fun loading() = execute {
        reduce { copy(isLoading = true) }
        delay(4000L)
        reduce { copy(isLoading = false) }
    }
}
