package team.noweekend.feature.home.mvi

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.collections.immutable.toImmutableMap
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.datetime.LocalDate
import team.noweekend.core.common.android.base.MVIViewModel
import team.noweekend.core.common.kotlin.extension.parseLocalDateString
import team.noweekend.core.common.ui.calendar.model.CalendarDateOfWeek
import team.noweekend.core.common.ui.calendar.model.CalendarWeeksData
import team.noweekend.core.common.ui.calendar.state.CalendarPagerState.Companion.initialPage
import team.noweekend.core.domain.usecase.CalendarDataProviderUseCase
import team.noweekend.core.domain.usecase.GetHolidayUseCase
import team.noweekend.core.domain.usecase.GetSandwichRecommendVacationUseCase
import team.noweekend.core.domain.usecase.GetUserProfileUseCase
import team.noweekend.core.domain.usecase.GetWeatherRecommendVacationUseCase
import team.noweekend.core.model.calendar.WeeksData
import team.noweekend.core.model.vacation.VacationType
import team.noweekend.feature.home.mapper.toImageTypeWithId
import team.noweekend.feature.home.model.HolidayUiModel
import team.noweekend.feature.home.model.MonthlyVacationRecommendUiModel
import team.noweekend.feature.home.model.PopularVacationUiModel
import team.noweekend.feature.home.model.toPopularVacation
import team.noweekend.feature.home.model.toUiModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getHolidayUseCase: GetHolidayUseCase,
    private val getWeatherRecommendVacationUseCase: GetWeatherRecommendVacationUseCase,
    private val getSandwichRecommendVacationUseCase: GetSandwichRecommendVacationUseCase,
    private val getUserProfileUseCase: GetUserProfileUseCase,
    private val calendarDataProviderUseCase: CalendarDataProviderUseCase,
) : MVIViewModel<HomeIntent, HomeSideEffect, HomeUiState>(
    savedStateHandle = savedStateHandle,
) {
    init {
        getPopularRecommendVacations()
        getRemainedHolidays()
        getWeatherRecommendVacation()
        getCalendarData()
        getUserProfile()
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

            is HomeIntent.ClickHolidayVacationCard,
            is HomeIntent.ClickPopularVacationClick,
            is HomeIntent.ClickRecommendationVacationCard,
                -> {
                updateTaskTitleBottomSheetVisibility(true)
            }

            else -> {}
        }
    }

    private fun getUserProfile() = execute {
        getUserProfileUseCase.invoke()
            .onSuccess { userProfile ->
                reduce {
                    copy(
                        averageTemperature = userProfile.averageTemperature,
                        remainingAnnualLeave = userProfile.remainingAnnualLeave,
                    )
                }
            }
    }

    private fun getPopularRecommendVacations() = execute {
        val remainedHolidaysDeferred = async { getHolidayUseCase.getRemainedHolidays() }
        val sandwichRecommendationsDeferred = async { getSandwichRecommendVacationUseCase.invoke() }
        val userProfileDeferred = async { getUserProfileUseCase.invoke() }

        val remainedRecentHolidays = remainedHolidaysDeferred.await()
            .getOrNull()?.firstOrNull()?.toPopularVacation()

        val sandwichRecommendations = sandwichRecommendationsDeferred.await()
            .getOrNull()?.toPopularVacation()

        val userBirth = userProfileDeferred.await()
            .getOrNull()?.userBirth

        val userBirthVacation = userBirth?.let { birth ->
            PopularVacationUiModel(
                vacationType = VacationType.BIRTHDAY_EXIST,
                startLocalDate = LocalDate.parseLocalDateString(birth),
                endLocalDate = null,
            )
        }

        reduce {
            copy(
                popularVacations = listOfNotNull(
                    remainedRecentHolidays,
                    sandwichRecommendations,
                    userBirthVacation,
                ).toImmutableList(),
            )
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

    private fun getCalendarData() = execute {
        calendarDataProviderUseCase.initWeekCalendar(initialPage)
        val weeksDate: Map<Int, WeeksData> = calendarDataProviderUseCase.weeksDate.value

        val calendarDateOfWeek = weeksDate.toList().associate { (key, value) ->
            key to CalendarWeeksData(
                year = value.year,
                month = value.month,
                calendarDateOfWeeks = value.dateOfWeeks.map { dateOfWeekList ->
                    dateOfWeekList.map { dateOfWeek ->
                        CalendarDateOfWeek(
                            calendarImageType = dateOfWeek.imageType.toImageTypeWithId(),
                            localDate = dateOfWeek.localDate,
                            isCurrentDate = dateOfWeek.isCurrentDate,
                        )
                    }.toImmutableList()
                }.toImmutableList(),
            )
        }.toImmutableMap()

        reduce {
            copy(
                calendarData = calendarDateOfWeek,
            )
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

    private fun updateTaskTitleBottomSheetVisibility(showBottomSheet: Boolean) {
        reduce { copy(showTaskTitleBottomSheet = showBottomSheet) }
    }
}
