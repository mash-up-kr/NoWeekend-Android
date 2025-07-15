package team.noweekend.feature.home.mvi

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import team.noweekend.core.common.android.base.MVIViewModel
import team.noweekend.core.domain.usecase.GetHolidayUseCase
import team.noweekend.core.domain.usecase.GetSandwichRecommendVacationUseCase
import team.noweekend.core.domain.usecase.GetWeatherRecommendVacationUseCase
import team.noweekend.core.domain.usecase.UserLocationUseCase
import team.noweekend.feature.home.model.HolidayUiModel
import team.noweekend.feature.home.model.MonthlyVacationRecommendUiModel
import team.noweekend.feature.home.model.toPopularVacation
import team.noweekend.feature.home.model.toUiModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getHolidayUseCase: GetHolidayUseCase,
    private val getWeatherRecommendVacationUseCase: GetWeatherRecommendVacationUseCase,
    private val userLocationUseCase: UserLocationUseCase,
    private val getSandwichRecommendVacationUseCase: GetSandwichRecommendVacationUseCase,
) : MVIViewModel<HomeIntent, HomeSideEffect, HomeUiState>(
    savedStateHandle = savedStateHandle,
) {
    init {
        getPopularRecommendVacations()
        getRemainedHolidays()
        saveUserLocation()
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


    private fun getPopularRecommendVacations() = execute {
        val remainedHolidaysDeferred = async { getHolidayUseCase.getRemainedHolidays() }
        val sandwichRecommendationsDeferred = async { getSandwichRecommendVacationUseCase.invoke() }
        val userProfileDeferred = async { }

        val remainedRecentHolidays = remainedHolidaysDeferred.await().getOrNull()?.firstOrNull()?.toPopularVacation()
        val sandwichRecommendations = sandwichRecommendationsDeferred.await().getOrNull()?.toPopularVacation()

        reduce {
            copy(popularVacations = listOfNotNull(remainedRecentHolidays, sandwichRecommendations).toImmutableList())
        }
    }

    private fun getRemainedHolidays() = execute {
        getHolidayUseCase.getRemainedHolidays()
            .onSuccess { remoteHolidays ->
                val holidays: List<HolidayUiModel> = remoteHolidays.map { it.toUiModel() }
                reduce { copy(remainedHolidays = holidays.toImmutableList()) }
            }
            .onFailure { exception ->
                Log.d("logtag", "$exception")
            }
    }


    private fun saveUserLocation() = execute {
        userLocationUseCase.saveLocation()
            .onSuccess {
                getWeatherRecommendVacation()
            }
            .onFailure { exception ->
                Log.d("logtag", "$exception")
            }
    }

    private fun getWeatherRecommendVacation() = execute {
        getWeatherRecommendVacationUseCase()
            .onSuccess { remoteVacations ->
                val weatherRecommendVacations: List<MonthlyVacationRecommendUiModel> = remoteVacations
                    .map { it.toUiModel() }

                reduce { copy(weatherRecommendVacations = weatherRecommendVacations.toImmutableList()) }
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
