package team.noweekend.feature.calendar.mvi

import team.noweekend.core.common.android.mvi.Intent
import team.noweekend.core.common.ui.calendar.model.CalendarDateOfWeek
import team.noweekend.core.common.ui.calendar.model.CalendarMode

sealed interface CalendarIntent : Intent {

    data class InitCalendar(val initPage: Int) : CalendarIntent
    data class UpdatePreviousWeeksData(val page: Int) : CalendarIntent
    data class UpdateNextWeeksData(val page: Int) : CalendarIntent
    data class UpdatePreviousMonthsData(val page: Int) : CalendarIntent
    data class UpdateNextMonthsData(val page: Int) : CalendarIntent

    data class UpdateTargetDate(val calendarDateOfWeek: CalendarDateOfWeek) : CalendarIntent
    data class UpdateChooserMonth(
        val page: Int,
        val calendarMode: CalendarMode,
    ) : CalendarIntent

    data object UpdateCalendarData : CalendarIntent
    data object CollectCalendarEvent : CalendarIntent

    data object UpdateCalendarMode : CalendarIntent
    data class UpdateCalendarModeWithToggleState(val isMonth: Boolean) : CalendarIntent

    data class UpdateCalendarDataAndChooser(val page: Int, val calendarMode: CalendarMode) : CalendarIntent

}
