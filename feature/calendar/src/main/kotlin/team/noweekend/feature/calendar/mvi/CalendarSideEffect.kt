package team.noweekend.feature.calendar.mvi

import team.noweekend.core.common.android.mvi.SideEffect

sealed interface CalendarSideEffect : SideEffect {
    data object CompleteInitMonthCalendar : CalendarSideEffect
    data object CompleteInitWeekCalendar : CalendarSideEffect

    data object CollectWeekPagerStatePage : CalendarSideEffect
    data object CollectMonthPagerStatePage : CalendarSideEffect
    data class UpdateWeekCalendar(val currentPage: Int) : CalendarSideEffect
    data class UpdateMonthCalendar(val currentPage : Int) :CalendarSideEffect

}
