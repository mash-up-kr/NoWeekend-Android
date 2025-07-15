package team.noweekend.feature.home.mvi

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.delay
import kotlinx.datetime.LocalDate
import team.noweekend.core.common.android.base.MVIViewModel
import team.noweekend.core.common.kotlin.extension.now
import team.noweekend.core.domain.usecase.GetHolidayUseCase
import team.noweekend.feature.home.model.HolidayUiModel
import team.noweekend.feature.home.model.toUiModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getHolidayUseCase: GetHolidayUseCase,
) : MVIViewModel<HomeIntent, HomeSideEffect, HomeUiState>(
    savedStateHandle = savedStateHandle,
) {
    init {
        getRemainedHolidays()
        getHolidays()
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): HomeUiState {
        return HomeUiState.INITIAL_STATE
    }

    override fun handleClientException(throwable: Throwable) {}

    override suspend fun handleIntent(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.CreateVacation -> {
                updateCreateVacationStatus(CreateVacationStatus.InProgress)
                delay(5000L) // TODO (JaesungLeee) : API 연동
                updateCreateVacationStatus(CreateVacationStatus.Complete)
            }

            is HomeIntent.ClickCreateVacation -> {
                navigateToCreateVacation()
            }

            else -> {}
        }
    }

    private fun getHolidays(requestDate: LocalDate = LocalDate.now()) = execute {
        getHolidayUseCase.getMonthlyHolidays(requestDate)
            .onSuccess { remoteHolidays ->
                val holidays: List<HolidayUiModel> = remoteHolidays.map { it.toUiModel() }
                reduce { copy(monthlyHolidays = holidays.toImmutableList()) }
            }
            .onFailure { exception ->
                Log.d("logtag", "$exception")
            }
    }

    private fun getRemainedHolidays() = execute {
        getHolidayUseCase.getRemainedHolidays()
            .onSuccess { remoteHolidays ->
                Log.d("logtag", "$remoteHolidays")
                val holidays: List<HolidayUiModel> = remoteHolidays.map { it.toUiModel() }
                reduce { copy(remainedHolidays = holidays.toImmutableList()) }
            }
            .onFailure { exception ->
                Log.d("logtag", "$exception")
            }
    }

    private fun navigateToCreateVacation() = execute {
        postSideEffect(
            HomeSideEffect.NavigateToCreateVacation(
                intentBuilder = {
                    putExtra("CREATE_VACATION_STATUS", currentState.createVacationStatus.tag)
                },
            ),
        )
    }

    private fun updateCreateVacationStatus(status: CreateVacationStatus) {
        reduce { copy(createVacationStatus = status) }
    }
}
