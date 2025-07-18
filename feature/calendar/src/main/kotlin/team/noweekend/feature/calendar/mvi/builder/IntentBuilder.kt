package team.noweekend.feature.calendar.mvi.builder

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import kotlinx.datetime.LocalDate
import team.noweekend.core.common.ui.calendar.model.CalendarDateOfWeek
import team.noweekend.core.common.ui.calendar.model.CalendarMode
import team.noweekend.core.common.ui.calendar.state.CalendarPagerState.Companion.initialPage
import team.noweekend.feature.calendar.mvi.CalendarIntent


@Composable
fun rememberIntentBuilder(
    send: (CalendarIntent) -> Unit,
) = remember {
    IntentBuilder(send = send)
}

@Stable
class IntentBuilder(
    private val send: (CalendarIntent) -> Unit,
) {

    private fun build(calendarIntent: CalendarIntent) {
        send(calendarIntent)
    }

    fun updatePreviousWeekPage(page: Int) {
        build(CalendarIntent.UpdatePreviousWeeksData(page = page))
    }

    fun updateNextWeekPage(page: Int) {
        build(CalendarIntent.UpdateNextWeeksData(page = page))
    }

    fun updatePreviousMonthPage(page: Int) {
        build(CalendarIntent.UpdatePreviousMonthsData(page = page))
    }

    fun updateNextMonthPage(page: Int) {
        build(CalendarIntent.UpdateNextMonthsData(page = page))
    }

    fun updateWeekCalendarAndChooser(page: Int) {
        build(
            CalendarIntent.UpdateCalendarDataAndChooser(
                page = page,
                calendarMode = CalendarMode.WEEK,
            ),
        )
    }

    fun updateMonthCalendarAndChooser(page: Int) {
        build(
            CalendarIntent.UpdateCalendarDataAndChooser(
                page = page,
                calendarMode = CalendarMode.MONTH,
            ),
        )
    }

    fun updateCalendarMode() {
        build(CalendarIntent.UpdateCalendarMode)
    }

    fun updateCalendarModeWithToggleState(isMonth: Boolean) {
        build(CalendarIntent.UpdateCalendarModeWithToggleState(isMonth = isMonth))
    }

    fun updateTargetDate(calendarDateOfWeek: CalendarDateOfWeek) {
        build(CalendarIntent.UpdateTargetDate(calendarDateOfWeek = calendarDateOfWeek))
    }

    fun collectCalendarEvent() {
        build(CalendarIntent.CollectCalendarEvent)
    }

    fun updateCalendarData() {
        build(CalendarIntent.UpdateCalendarData)
    }

    fun initCalendarData() {
        build(CalendarIntent.InitCalendar(initPage = initialPage))
    }

    fun initCalendarDateWithDate(
        targetDate: LocalDate,
    ){
        build(CalendarIntent.InitCalendarWithDate(initPage = initialPage, targetDate = targetDate))
    }


    fun updateTodoList(targetDate: LocalDate) {
        build(CalendarIntent.UpdateTodoList(targetDate = targetDate))
    }

    fun changeCompleteSchedule(index: Int) {
        build(CalendarIntent.ChangeComplete(index = index))
    }

    fun updateCalendarState() {
        build(CalendarIntent.UpdateCalendarState)
    }

    fun getRecommendTodoTagList() {
        build(CalendarIntent.GetRecommendTodoTagList)
    }

    fun clickRecommendTodoTag(index: Int) {
        build(CalendarIntent.ClickRecommendTodoTag(index = index))
    }

    fun clickMonthChooser(){
        build(CalendarIntent.ClickMonthChooser)
    }

}
