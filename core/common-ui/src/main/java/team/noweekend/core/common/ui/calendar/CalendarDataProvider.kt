package team.noweekend.core.common.ui.calendar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateMap
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.plus
import team.noweekend.core.common.kotlin.extension.CalendarUtils.firstDayOfMonth
import team.noweekend.core.common.kotlin.extension.CalendarUtils.lastDayOfMonth
import team.noweekend.core.common.kotlin.extension.CalendarUtils.minusMonths
import team.noweekend.core.common.kotlin.extension.CalendarUtils.minusWeeks
import team.noweekend.core.common.kotlin.extension.CalendarUtils.nextOrSame
import team.noweekend.core.common.kotlin.extension.CalendarUtils.plusDays
import team.noweekend.core.common.kotlin.extension.CalendarUtils.plusMonths
import team.noweekend.core.common.kotlin.extension.CalendarUtils.plusWeeks
import team.noweekend.core.common.kotlin.extension.CalendarUtils.previousOrSame
import team.noweekend.core.common.kotlin.extension.now
import team.noweekend.core.common.ui.calendar.model.CalendarDateOfWeek
import team.noweekend.core.common.ui.calendar.model.CalendarImageType
import team.noweekend.core.common.ui.calendar.model.CalendarWeeksData

@Composable
fun rememberCalendarDataProvider(): CalendarDataProvider {
    return remember {
        CalendarDataProvider()
    }
}

@Stable
class CalendarDataProvider {

    private val _targetDate: MutableState<LocalDate> = mutableStateOf(LocalDate.now())

    val targetDate: State<LocalDate> = _targetDate

    private val TAG: String = "CalendarDataProvider"

    private val _calendarDataProviderEventChannel: Channel<CalendarDataProviderEvent> =
        Channel(capacity = Channel.BUFFERED)

    val calendarDataProviderEventFlow: Flow<CalendarDataProviderEvent> =
        _calendarDataProviderEventChannel.receiveAsFlow()

    val calendarWeeksData: SnapshotStateMap<Int, CalendarWeeksData> = mutableStateMapOf()

    val monthData: SnapshotStateMap<Int, CalendarWeeksData> = mutableStateMapOf()

    /**
     * 주 데이터 를 반환
     */
    private fun getWeekDates(startedMonday: LocalDate): CalendarWeeksData {
        return CalendarWeeksData(
            year = startedMonday.year,
            month = startedMonday.monthNumber,
            calendarDateOfWeeks = (0..6).map { day: Int ->
                val localDate: LocalDate = startedMonday.plusDays(day)
                CalendarDateOfWeek(
                    calendarImageType = CalendarImageType.entries.random(),
                    localDate = localDate,
                    isCurrentDate = localDate == LocalDate.now(),
                )
            }.chunked(7).map { weeks: List<CalendarDateOfWeek> -> weeks.toImmutableList() }
                .toImmutableList(),
        )
    }

    private fun getWeekDates(startedMonday: LocalDate, month: Int): CalendarWeeksData {
        return CalendarWeeksData(
            year = startedMonday.year,
            month = month,
            calendarDateOfWeeks = (0..6).map { day: Int ->
                val localDate: LocalDate = startedMonday.plusDays(day)
                CalendarDateOfWeek(
                    calendarImageType = CalendarImageType.entries.random(),
                    localDate = localDate,
                    isCurrentDate = localDate == LocalDate.now(),
                )
            }.chunked(7).map { weeks: List<CalendarDateOfWeek> -> weeks.toImmutableList() }
                .toImmutableList(),
        )
    }

    /**
     * 주 캘린더 데이터 초기화
     */
    suspend fun initWeekCalendar(
        initPage: Int,
    ) {
        val currentDay: LocalDate = LocalDate.now()
        _targetDate.value = currentDay
        val targetWeekMonday: LocalDate = currentDay.previousOrSame(DayOfWeek.MONDAY)

        calendarWeeksData.clear()
        calendarWeeksData[initPage] = getWeekDates(startedMonday = targetWeekMonday, month = currentDay.monthNumber)
        calendarWeeksData[initPage - 1] = getWeekDates(startedMonday = targetWeekMonday.minusWeeks(1))
        calendarWeeksData[initPage + 1] = getWeekDates(startedMonday = targetWeekMonday.plusWeeks(1))

        _calendarDataProviderEventChannel.send(
            element = CalendarDataProviderEvent.CompleteInitWeeksCalendar,
        )
    }

    /**
     * 현재 페이지 의 이전 페이지 주 데이터 update
     */
    fun updatePreviousWeeksData(currentPage: Int) {
        val offsetPageData =
            calendarWeeksData[currentPage] ?: throw IllegalStateException("offsetPageData $currentPage is null")

        val currentWeekMonday: CalendarDateOfWeek = offsetPageData.calendarDateOfWeeks.flatten().first()

        val prevPage = currentPage - 1
        calendarWeeksData[prevPage] = getWeekDates(
            startedMonday = currentWeekMonday.localDate.minusWeeks(1),
        )

        val removeNeededPage = currentPage + 2
        calendarWeeksData.remove(removeNeededPage)
    }

    /**
     * 현재 페이지 의 다음 페이지 주 데이터 update
     */
    fun updateNextWeeksData(currentPage: Int) {
        val offsetPageData = calendarWeeksData[currentPage] ?: throw IllegalStateException("offsetPageData is null")
        val currentWeekMonday: CalendarDateOfWeek = offsetPageData.calendarDateOfWeeks.flatten().first()

        val nextPageOffset = currentPage + 1

        calendarWeeksData[nextPageOffset] = getWeekDates(
            startedMonday = currentWeekMonday.localDate.plusWeeks(1),
        )

        val removeNeededPage = currentPage - 2
        calendarWeeksData.remove(removeNeededPage)
    }

    /**
     * 월 캘린더 데이터 초기화
     */
    suspend fun initMonthCalendar(page: Int, chooserMonth: LocalDate) {
        monthData.clear()
        monthData[page] = getMonthDates(monthStart = chooserMonth)
        monthData[page - 1] = getMonthDates(monthStart = chooserMonth.minusMonths(1))
        monthData[page + 1] = getMonthDates(monthStart = chooserMonth.plusMonths(1))

        _calendarDataProviderEventChannel.send(
            element = CalendarDataProviderEvent.CompleteInitMonthCalendar,
        )
    }

    /**
     * 현재 페이지 의 이전 페이지 월 데이터 update
     */
    fun updatePreviousMonthData(currentPage: Int) {
        val currentMonthLocalDate: LocalDate = getCurrentMonthLocalDate(currentPage = currentPage)
        val prevPage = currentPage - 1
        monthData[prevPage] = getMonthDates(monthStart = currentMonthLocalDate.minusMonths(1))

        val removeNeededPage = currentPage + 2
        monthData.remove(removeNeededPage)
    }

    /**
     * 현재 페이지 의 다음 페이지 월 데이터 update
     */
    fun updateNextMonthData(currentPage: Int) {
        val currentMonthLocalDate: LocalDate = getCurrentMonthLocalDate(currentPage = currentPage)
        val nextPage = currentPage + 1
        monthData[nextPage] = getMonthDates(monthStart = currentMonthLocalDate.plusMonths(1))

        val removeNeededPage = currentPage - 2
        monthData.remove(removeNeededPage)
    }

    /**
     * 월의 첫 날이 포함된 주의 월요일부터 마지막 날이 포함된 주의 일요일까지 날짜 리스트 반환
     */
    private fun getMonthDates(monthStart: LocalDate): CalendarWeeksData {
        /**
         * 주어진 월의 첫 날
         */
        val firstDayOfMonth: LocalDate = monthStart.firstDayOfMonth()

        /**
         * 주어진 월의 첫 날이 포함된 주의 월요일
         */
        val firstMondayOfWeekContainingFirstDay: LocalDate = firstDayOfMonth.previousOrSame(DayOfWeek.MONDAY)

        /**
         * 주어진 월의 마지막 날
         */
        val monthEnd: LocalDate = monthStart.lastDayOfMonth()

        /**
         * 주어진 월의 마지막 날이 포함된 주의 일요일
         */
        val lastSundayOfWeekContainingLastDay: LocalDate = monthEnd.nextOrSame(DayOfWeek.SUNDAY)

        /**
         * 주어진 월의 첫 날이 포함된 주의 월요일 부터 마지막 날이 포함된 주의 일요일 까지의 일 수
         */
        val daysInPeriod: Int = generateSequence(firstMondayOfWeekContainingFirstDay) { localDate ->
            localDate.plus(1, DateTimeUnit.DAY)
        }.takeWhile { it <= lastSundayOfWeekContainingLastDay }
            .count()

        return CalendarWeeksData(
            year = monthStart.year,
            month = monthStart.monthNumber,
            calendarDateOfWeeks = (0 until daysInPeriod).map { day: Int ->
                val localDate: LocalDate = firstMondayOfWeekContainingFirstDay.plusDays(day)
                CalendarDateOfWeek(
                    calendarImageType = CalendarImageType.entries.random(),
                    localDate = localDate,
                    isCurrentDate = localDate == LocalDate.now(),
                )
            }.chunked(7).map { weeks: List<CalendarDateOfWeek> ->
                weeks.toImmutableList()
            }.toImmutableList(),
        )
    }

    /**
     * [currentPage] pagerState의 currentPage
     * 월의 시작이 다른 달일 수도 있어 정확한 현재 달의 LocalDate 값을 반환.
     */
    private fun getCurrentMonthLocalDate(currentPage: Int): LocalDate {
        val currentMonthData: CalendarWeeksData = monthData[currentPage] ?: throw IllegalStateException(
            "currentPage is null",
        )
        val currentMonth: Int = currentMonthData.month
        val currentMonthLocalCalendarDateOfWeek: CalendarDateOfWeek =
            currentMonthData.calendarDateOfWeeks.flatten().first { calendarDateOfWeek: CalendarDateOfWeek ->
                calendarDateOfWeek.localDate.monthNumber == currentMonth
            }
        return currentMonthLocalCalendarDateOfWeek.localDate
    }

    sealed interface CalendarDataProviderEvent {
        data object CompleteInitWeeksCalendar : CalendarDataProviderEvent
        data object CompleteInitMonthCalendar : CalendarDataProviderEvent
    }
}
