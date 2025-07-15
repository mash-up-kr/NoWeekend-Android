package team.noweekend.feature.detail.date.mvi

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.persistentListOf
import team.noweekend.core.common.android.base.MVIViewModel
import team.noweekend.core.domain.usecase.CalendarDataProviderUseCase
import team.noweekend.feature.detail.date.model.DegreeUIModel
import javax.inject.Inject


@HiltViewModel
class DetailDateViewModel @Inject constructor(
    private val calendarDateProviderUseCase: CalendarDataProviderUseCase,
    savedStateHandle: SavedStateHandle,
) : MVIViewModel<DetailDateIntent, DetailDateSideEffect, DetailDateUiState>(
    savedStateHandle = savedStateHandle,
) {

    override fun createInitialState(savedStateHandle: SavedStateHandle): DetailDateUiState {
        return DetailDateUiState(
            dateTitle = savedStateHandle.get<String>("date") ?: "",
            degreeUiModel = DegreeUIModel(degree = 0, isAnnualLeave = false),
            todoList = persistentListOf(),
        )
    }

    override fun onCleared() {
        super.onCleared()
    }

    override fun handleClientException(throwable: Throwable) {
    }

    override suspend fun handleIntent(intent: DetailDateIntent) {

    }

}
