package team.noweekend.feature.onboarding.mvi

import kotlinx.collections.immutable.ImmutableList
import team.noweekend.core.common.android.mvi.Intent
import team.noweekend.core.common.ui.schedule.model.FrequentSchedule

sealed interface OnboardIntent : Intent {
    data class ClickProfileNext(val nickname: String, val birth: String) : OnboardIntent
    data object ClickBack : OnboardIntent
    data class ClickVacationNext(val vacationDay: String) : OnboardIntent
    data class ClickHalfVacation(val isToggle: Boolean) : OnboardIntent
    data class ClickFinishOnboarding(val tagList: ImmutableList<FrequentSchedule>) : OnboardIntent
    data class SelectTag(val tag: FrequentSchedule) : OnboardIntent
}
