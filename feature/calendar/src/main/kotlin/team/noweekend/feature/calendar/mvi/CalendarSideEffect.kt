package team.noweekend.feature.calendar.mvi

import kotlinx.datetime.LocalDate
import team.noweekend.core.common.android.mvi.SideEffect

sealed interface CalendarSideEffect : SideEffect {
    data object CompleteInitMonthCalendar : CalendarSideEffect
    data object CompleteInitWeekCalendar : CalendarSideEffect

    data object CollectWeekPagerStatePage : CalendarSideEffect
    data object CollectMonthPagerStatePage : CalendarSideEffect
    data class UpdateWeekCalendarPage(val currentPage: Int) : CalendarSideEffect
    data class UpdateMonthCalendarPage(val currentPage: Int) : CalendarSideEffect

    data class NavigateToDetailDate(val date: String) : CalendarSideEffect


}
