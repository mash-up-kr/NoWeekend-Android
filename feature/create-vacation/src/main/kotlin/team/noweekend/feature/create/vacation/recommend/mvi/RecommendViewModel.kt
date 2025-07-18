package team.noweekend.feature.create.vacation.recommend.mvi

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import team.noweekend.core.common.android.base.MVIViewModel
import team.noweekend.core.domain.usecase.GetRecommendVacationResultUseCase
import team.noweekend.feature.create.vacation.recommend.model.RecommendVacationType
import team.noweekend.feature.create.vacation.recommend.model.RecommendedVacationUiModel
import javax.inject.Inject

@HiltViewModel
class RecommendViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getRecommendVacationResultUseCase: GetRecommendVacationResultUseCase,
) : MVIViewModel<RecommendIntent, RecommendSideEffect, RecommendUiState>(
    savedStateHandle = savedStateHandle,
) {
    init {
        getRecommendVacationResult()
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): RecommendUiState {
        return RecommendUiState.INITIAL_STATE
    }

    override fun handleClientException(throwable: Throwable) {

    }

    override suspend fun handleIntent(intent: RecommendIntent) {
        when (intent) {
            is RecommendIntent.ClickBackButton -> navigateToHistoryBack()
            is RecommendIntent.ClickRecommendedDate -> {
                // TODO(JaesungLeee) : TODO 입력 바텀시트 노출
            }
        }
    }

    private fun navigateToHistoryBack() = execute {
        postSideEffect(RecommendSideEffect.NavigateToHistoryBack)
    }

    private fun getRecommendVacationResult() = execute {
        reduce { copy(isLoading = true) }
        delay(1200L)
        getRecommendVacationResultUseCase.invoke()
            .onSuccess {
                reduce {
                    copy(
                        recommendedVacation = RecommendedVacationUiModel.INITIAL_DATA.copy(
                            recommendedContent = it.title,
                            vacationType = RecommendVacationType.LOCAL,
                        ),
                    )
                }
            }
            .onFailure {
                Log.d("logtag", "$it")
            }
            .also { reduce { copy(isLoading = false) } }
    }
}
