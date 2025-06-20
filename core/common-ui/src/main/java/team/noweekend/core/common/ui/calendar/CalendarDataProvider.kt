package team.noweekend.core.common.ui.calendar

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshots.SnapshotStateMap
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.plus
import team.noweekend.core.common.ui.calendar.model.DateOfWeek
import team.noweekend.core.common.ui.calendar.model.WeeksData
import team.noweekend.core.common.ui.calendar.util.CalendarUtils.firstDayOfMonth
import team.noweekend.core.common.ui.calendar.util.CalendarUtils.lastDayOfMonth
import team.noweekend.core.common.ui.calendar.util.CalendarUtils.minusMonths
import team.noweekend.core.common.ui.calendar.util.CalendarUtils.minusWeeks
import team.noweekend.core.common.ui.calendar.util.CalendarUtils.nextOrSame
import team.noweekend.core.common.ui.calendar.util.CalendarUtils.now
import team.noweekend.core.common.ui.calendar.util.CalendarUtils.plusDays
import team.noweekend.core.common.ui.calendar.util.CalendarUtils.plusMonths
import team.noweekend.core.common.ui.calendar.util.CalendarUtils.plusWeeks
import team.noweekend.core.common.ui.calendar.util.CalendarUtils.previousOrSame
import team.noweekend.core.resource.R

@Composable
fun rememberCalendarDataProvider(
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
): CalendarDataProvider {
    return remember {
        CalendarDataProvider(
            coroutineScope = coroutineScope,
        )
    }
}

@Stable
class CalendarDataProvider(
    private val coroutineScope: CoroutineScope,
) {

    private val _targetDate: MutableState<LocalDate> = mutableStateOf(now())

    val targetDate: State<LocalDate> = _targetDate

    private val TAG: String = "CalendarDataProvider"

    private val _calendarDataProviderEventChannel: Channel<CalendarDataProviderEvent> =
        Channel(capacity = Channel.BUFFERED)

    val calendarDataProviderEventFlow: Flow<CalendarDataProviderEvent> =
        _calendarDataProviderEventChannel.receiveAsFlow()

    private val currentWeekMonday: LocalDate = _targetDate.value.previousOrSame(
        DayOfWeek.MONDAY,
    )

    private val currentMonthStart: LocalDate = _targetDate.value.firstDayOfMonth()

    val weeksData: SnapshotStateMap<Int, WeeksData> = mutableStateMapOf()

//    val weeksData: SnapshotStateList<WeeksData> = mutableListOf(
//        getWeekDates(startedMonday = currentWeekMonday.minusWeeks(1)),
//        getWeekDates(startedMonday = currentWeekMonday),
//        getWeekDates(startedMonday = currentWeekMonday.plusWeeks(1)),
//    ).toMutableStateList()

    val monthData: SnapshotStateMap<Int, WeeksData> = mutableStateMapOf()

    /**
     * 주 데이터 를 반환
     */
    private fun getWeekDates(startedMonday: LocalDate): WeeksData {
        return WeeksData(
            year = startedMonday.year,
            month = startedMonday.monthNumber,
            dateOfWeeks = (0..6).map { day: Int ->
                val localDate: LocalDate = startedMonday.plusDays(day)
                DateOfWeek(
                    imageType = ImageType.entries.random(), // Todo 이미지 로직
                    localDate = localDate,
                )
            }.chunked(7).map { weeks: List<DateOfWeek> -> weeks.toImmutableList() }
                .toImmutableList(),
        )
    }

    /**
     * 주 캘린더 데이터 초기화
     */
    fun initWeekCalendar(
        initPage: Int,
    ) {
        val targetWeekMonday: LocalDate = _targetDate.value.previousOrSame(DayOfWeek.MONDAY)

        weeksData.clear()
        weeksData[initPage] = getWeekDates(startedMonday = targetWeekMonday)
        weeksData[initPage - 1] = getWeekDates(startedMonday = targetWeekMonday.minusWeeks(1))
        weeksData[initPage + 1] = getWeekDates(startedMonday = targetWeekMonday.plusWeeks(1))

        coroutineScope.launch {
            _calendarDataProviderEventChannel.send(
                element = CalendarDataProviderEvent.CompleteInitWeeksCalendar,
            )
        }
    }

    /**
     * 현재 페이지 의 이전 페이지 주 데이터 update
     */
    fun updatePreviousWeeksData(currentPage: Int) {
        val offsetPageData =
            weeksData[currentPage] ?: throw IllegalStateException("offsetPageData $currentPage is null")

        val currentWeekMonday: DateOfWeek = offsetPageData.dateOfWeeks.flatten().first()

        val prevPage = currentPage - 1
        weeksData[prevPage] = getWeekDates(
            startedMonday = currentWeekMonday.localDate.minusWeeks(1),
        )

        val removeNeededPage = currentPage + 2
        weeksData.remove(removeNeededPage)?.let { _ ->
            log("currentPage = $currentPage, $removeNeededPage is removed")
        }

        log("UpdatePreviousWeeksData\n${weeksData.entries.joinToString("\n") { it.key.toString() + " " + it.value }}")
    }

    /**
     * 현재 페이지 의 다음 페이지 주 데이터 update
     */
    fun updateNextWeeksData(currentPage: Int) {
        val offsetPageData = weeksData[currentPage] ?: throw IllegalStateException("offsetPageData is null")
        val currentWeekMonday: DateOfWeek = offsetPageData.dateOfWeeks.flatten().first()

        val nextPageOffset = currentPage + 1

        weeksData[nextPageOffset] = getWeekDates(
            startedMonday = currentWeekMonday.localDate.plusWeeks(1),
        )

        val removeNeededPage = currentPage - 2
        weeksData.remove(removeNeededPage)?.let { _ ->
            log("currentPage = $currentPage, $removeNeededPage is removed")
        }

        log("UpdateNextWeeksData\n${weeksData.entries.joinToString("\n") { it.key.toString() + " " + it.value }}")
    }

    /**
     * 월 캘린더 데이터 초기화
     */
    fun initMonthCalendar(page: Int) {
        val key: Int = weeksData.entries.map { it.key }.sorted().getOrNull(1) ?: page
        val weekData: WeeksData = weeksData.getOrDefault(key = key, defaultValue = WeeksData.default)
        val localDate: LocalDate = LocalDate(
            weekData.year,
            weekData.month,
            1,
        )
        monthData.clear()
        monthData[page] = getMonthDates(monthStart = localDate)
        monthData[page - 1] = getMonthDates(monthStart = localDate.minusMonths(1))
        monthData[page + 1] = getMonthDates(monthStart = localDate.plusMonths(1))

        coroutineScope.launch {
            _calendarDataProviderEventChannel.send(
                element = CalendarDataProviderEvent.CompleteInitMonthCalendar,
            )
        }
    }

    /**
     * 현재 페이지 의 이전 페이지 월 데이터 update
     */
    fun updatePreviousMonthData(currentPage: Int) {
        val currentMonthLocalDate: LocalDate = getCurrentMonthLocalDate(currentPage = currentPage)
        val prevPage = currentPage - 1
        monthData[prevPage] = getMonthDates(monthStart = currentMonthLocalDate.minusMonths(1))

        val removeNeededPage = currentPage + 2
        monthData.remove(removeNeededPage)?.let { _ ->
            log("currentPage = $currentPage, $removeNeededPage is removed")
        }

        log("UpdatePreviousMonthData\n${monthData.entries.joinToString("\n") { it.key.toString() + " " + it.value }}")
    }

    /**
     * 현재 페이지 의 다음 페이지 월 데이터 update
     */
    fun updateNextMonthData(currentPage: Int) {
        val currentMonthLocalDate: LocalDate = getCurrentMonthLocalDate(currentPage = currentPage)
        val nextPage = currentPage + 1
        monthData[nextPage] = getMonthDates(monthStart = currentMonthLocalDate.plusMonths(1))

        val removeNeededPage = currentPage - 2
        monthData.remove(removeNeededPage)?.let { _ ->
            log("currentPage = $currentPage, $removeNeededPage is removed")
        }

        log("UpdatePreviousMonthData\n${monthData.entries.joinToString("\n") { it.key.toString() + " " + it.value }}")
    }

    /**
     * 월의 첫 날이 포함된 주의 월요일부터 마지막 날이 포함된 주의 일요일까지 날짜 리스트 반환
     */
    private fun getMonthDates(monthStart: LocalDate): WeeksData {
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

        return WeeksData(
            year = monthStart.year,
            month = monthStart.monthNumber,
            dateOfWeeks = (0 until daysInPeriod).map { day: Int ->
                val localDate: LocalDate = firstMondayOfWeekContainingFirstDay.plusDays(day)
                DateOfWeek(
                    imageType = ImageType.entries.random(), // Todo 이미지 로직
                    localDate = localDate,
                )
            }.chunked(7).map { weeks: List<DateOfWeek> ->
                weeks.toImmutableList()
            }.toImmutableList(),
        )
    }

    /**
     * [currentPage] pagerState의 currentPage
     * 월의 시작이 다른 달일 수도 있어 정확한 현재 달의 LocalDate 값을 반환.
     */
    private fun getCurrentMonthLocalDate(currentPage: Int): LocalDate {
        val currentMonthData: WeeksData = monthData[currentPage] ?: throw IllegalStateException("currentPage is null")
        val currentMonth: Int = currentMonthData.month
        val currentMonthLocalDateOfWeek: DateOfWeek =
            currentMonthData.dateOfWeeks.flatten().first { dateOfWeek: DateOfWeek ->
                dateOfWeek.localDate.monthNumber == currentMonth
            }
        return currentMonthLocalDateOfWeek.localDate
    }

    /**
     * 현재 선택된 날짜를 update.
     */
    fun updateTargetDate(dateOfWeek: DateOfWeek) {
        _targetDate.value = dateOfWeek.localDate
        log(message = "Target date updated to $_targetDate")
    }

    private fun log(message: String, isDebugLevel: Boolean = true) {
        if (isDebugLevel) {
            Log.d(TAG, message)
        } else {
            Log.e(TAG, message)
        }
    }

    sealed interface CalendarDataProviderEvent {
        data object CompleteInitWeeksCalendar : CalendarDataProviderEvent
        data object CompleteInitMonthCalendar : CalendarDataProviderEvent
    }

    enum class ImageType(@DrawableRes val id: Int) {
        NONE(id = R.drawable.ic_day_type_none),
        FutureSchedule(id = R.drawable.ic_day_type_future_schedule),
        BurnOut(id = R.drawable.ic_day_type_burnout),
        Rest(id = R.drawable.ic_day_type_rest),
        OverZeroUnderFiftyDegree(id = R.drawable.ic_day_type_over_zero_under_fifty_degree),
        OverFiftyUnderSeventyFive(id = R.drawable.ic_day_type_over_fifty_under_seventy_degree),
    }
}
