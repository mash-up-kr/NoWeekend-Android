package team.noweekend.feature.onboarding.mvi

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import team.noweekend.core.common.android.base.MVIViewModel
import team.noweekend.core.common.ui.schedule.model.FrequentSchedule
import team.noweekend.core.domain.usecase.GetOnboardTagsUseCase
import team.noweekend.core.domain.usecase.RegistrationProfileUseCase
import team.noweekend.core.domain.usecase.RegistrationTagsUseCase
import team.noweekend.core.domain.usecase.RegistrationVacationUseCase
import team.noweekend.core.domain.usecase.SaveOnboardFinishedUseCase
import team.noweekend.core.model.onboard.OnboardProfileParam
import team.noweekend.core.model.onboard.OnboardTagsParam
import team.noweekend.core.model.onboard.OnboardVacationParam
import javax.inject.Inject

@HiltViewModel
class OnboardViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getTagsUseCase: GetOnboardTagsUseCase,
    private val saveProfileUseCase: RegistrationProfileUseCase,
    private val saveVacationUseCase: RegistrationVacationUseCase,
    private val saveTags: RegistrationTagsUseCase,
    private val saveOnboardFinishedUseCase: SaveOnboardFinishedUseCase,
) : MVIViewModel<OnboardIntent, OnboardSideEffect, OnboardUiState>(savedStateHandle) {
    init {
        execute {
            getTagsUseCase()
                .onSuccess {
                    reduce {
                        copy(
                            scheduleTags = it.tags.map {
                                FrequentSchedule(
                                    title = it,
                                    isSelected = false,
                                )
                            }.toImmutableList(),
                        )
                    }
                }.onFailure {
                    // TODO: Handle error case
                }
        }
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): OnboardUiState {
        return OnboardUiState.INITIAL_STATE
    }

    override fun handleClientException(throwable: Throwable) {
        Unit
    }

    override suspend fun handleIntent(intent: OnboardIntent) {
        when (intent) {
            is OnboardIntent.ClickBack -> postSideEffect(OnboardSideEffect.NavigateToBack)
            is OnboardIntent.ClickFinishOnboarding -> {
                saveOnboardData()
                updateTagListState(intent.tagList)
            }

            is OnboardIntent.ClickProfileNext -> {
                updateProfileState(nickname = intent.nickname, birth = intent.birth)
                postSideEffect(OnboardSideEffect.NavigateToVacation)
            }

            is OnboardIntent.ClickVacationNext -> {
                updateVacationDay(intent.vacationDay)
                postSideEffect(OnboardSideEffect.NavigateToScheduleTag)
            }

            is OnboardIntent.ClickHalfVacation -> {
                updateVacationHour(intent.isToggle)
            }

            is OnboardIntent.SelectTag -> updateSelectTag(intent.tag)
        }
    }

    private fun updateSelectTag(tag: FrequentSchedule) {
        reduce {
            copy(
                scheduleTags = uiState.value.scheduleTags.map {
                    if (it.title == tag.title) {
                        it.copy(isSelected = !it.isSelected)
                    } else {
                        it
                    }
                }.toImmutableList(),
            )
        }
    }

    private fun saveOnboardData() {
        execute {
            val profileDeferred = async {
                saveProfileUseCase(
                    OnboardProfileParam(
                        nickname = uiState.value.nickname,
                        birthDate = uiState.value.birth,
                    ),
                )
            }

            val vacationDeferred = async {
                saveVacationUseCase(
                    OnboardVacationParam(
                        days = uiState.value.vacationDay,
                        hours = uiState.value.vacationHour,
                    ),
                )
            }

            val tagsDeferred = async {
                saveTags(
                    OnboardTagsParam(
                        scheduleTags = uiState.value.scheduleTags.map { it.title },
                    ),
                )
            }
            val result = awaitAll(profileDeferred, vacationDeferred, tagsDeferred)

            if (result.any { it.isFailure }) {
                Log.d("OnboardViewModel", "Failed to save onboard data")
                // TODO: Handle error case
            } else {
                Log.d("OnboardViewModel", "Successfully saved onboard data")
                saveOnboardFinishedUseCase(isFinished = true)
                postSideEffect(OnboardSideEffect.NavigateToHome)
            }
        }
    }

    private fun updateTagListState(tagList: ImmutableList<FrequentSchedule>) {
        reduce {
            copy(
                scheduleTags = tagList,
            )
        }
    }

    private fun updateVacationDay(vacationDay: String) {
        reduce {
            copy(
                vacationDay = vacationDay.toInt(),
            )
        }
    }

    private fun updateVacationHour(toggle: Boolean) {
        reduce {
            copy(
                vacationHour = if (toggle) {
                    4
                } else {
                    0
                },
            )
        }
    }

    private fun updateProfileState(nickname: String, birth: String) {
        reduce {
            copy(
                nickname = nickname,
                birth = birth,
            )
        }
    }
}
