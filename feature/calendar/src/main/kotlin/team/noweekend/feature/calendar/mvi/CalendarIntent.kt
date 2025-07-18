package team.noweekend.feature.calendar.mvi

import kotlinx.datetime.LocalDate
import team.noweekend.core.common.android.mvi.Intent
import team.noweekend.core.common.ui.calendar.model.CalendarDateOfWeek
import team.noweekend.core.common.ui.calendar.model.CalendarMode

sealed interface CalendarIntent : Intent {

    data class InitCalendar(val initPage: Int) : CalendarIntent
    data class InitCalendarWithDate(val initPage: Int, val targetDate: LocalDate) : CalendarIntent
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

    data class UpdateTodoList(val targetDate: LocalDate) : CalendarIntent
    data class ChangeComplete(val index: Int) : CalendarIntent

    data object UpdateCalendarState : CalendarIntent

    data object GetRecommendTodoTagList : CalendarIntent

    data class ClickRecommendTodoTag(val index: Int) : CalendarIntent

    data object ClickMonthChooser : CalendarIntent
    data object ClickDirectInput : CalendarIntent
    data class ClickTodoOption(val index: Int) : CalendarIntent
    data class EditTodo(val index: Int) : CalendarIntent
    data class DeleteTodo(val index: Int) : CalendarIntent
    data class AddSameTodo(val index: Int) : CalendarIntent

    data object DismissTodo : CalendarIntent


}
