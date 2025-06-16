package team.noweekend.core.common.ui.calendar

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import team.noweekend.core.common.ui.calendar.model.DateOfWeek
import team.noweekend.core.common.ui.calendar.model.WeeksData
import team.noweekend.core.common.ui.calendar.state.CalendarPagerState.ImageType
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters

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

    private val _targetDate: MutableState<LocalDate> = mutableStateOf(LocalDate.now())

    val targetDate: State<LocalDate> = _targetDate

    private val TAG: String = "CalendarDataProvider"

    private val _calendarDataProviderEventChannel: Channel<CalendarDataProviderEvent> =
        Channel(capacity = Channel.BUFFERED)

    val calendarDataProviderEventFlow: Flow<CalendarDataProviderEvent> =
        _calendarDataProviderEventChannel.receiveAsFlow()

    private val currentWeekMonday: LocalDate = _targetDate.value.with(
        TemporalAdjusters.previousOrSame(
            DayOfWeek.MONDAY,
        ),
    )

    private val currentMonthStart: LocalDate = _targetDate.value.with(
        TemporalAdjusters.firstDayOfMonth(),
    )

    val weeksData: SnapshotStateList<WeeksData> = mutableListOf(
        getWeekDates(startedMonday = currentWeekMonday.minusWeeks(1)),
        getWeekDates(startedMonday = currentWeekMonday),
        getWeekDates(startedMonday = currentWeekMonday.plusWeeks(1)),
    ).toMutableStateList()

    val monthData: SnapshotStateList<WeeksData> = mutableListOf(
        getMonthDates(monthStart = currentMonthStart.minusMonths(1)),
        getMonthDates(monthStart = currentMonthStart),
        getMonthDates(monthStart = currentMonthStart.plusMonths(1)),
    ).toMutableStateList()

    /**
     * 주 데이터 를 반환
     */
    private fun getWeekDates(startedMonday: LocalDate): WeeksData {
        return WeeksData(
            year = startedMonday.year,
            month = startedMonday.monthValue,
            dateOfWeeks = (0..6).map { day: Int ->
                val localDate: LocalDate = startedMonday.plusDays(day.toLong())
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
    fun initWeekCalendar() {
        val targetWeekMonday: LocalDate = _targetDate.value.with(
            TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY),
        )

        weeksData[0] = getWeekDates(startedMonday = targetWeekMonday.minusWeeks(1))
        weeksData[1] = getWeekDates(startedMonday = targetWeekMonday)
        weeksData[2] = getWeekDates(startedMonday = targetWeekMonday.plusWeeks(1))

        coroutineScope.launch {
            _calendarDataProviderEventChannel.send(
                element = CalendarDataProviderEvent.CompleteInitWeeksCalendar,
            )
        }
    }

    /**
     * 현재 페이지 의 이전 페이지 주 데이터 update
     */
    fun updatePreviousWeeksData(currentIndex: Int, prevIndex: Int) {
        val currentWeekMonday: DateOfWeek = weeksData[currentIndex].dateOfWeeks.flatten().first()
        weeksData[prevIndex] = getWeekDates(
            startedMonday = currentWeekMonday.localDate.minusWeeks(1),
        )
        log(
            message = "currentIndex = $currentIndex prevIndex = $prevIndex" +
                "weeksData = ${weeksData[prevIndex].dateOfWeeks.first()}",
        )
    }

    /**
     * 현재 페이지 의 다음 페이지 주 데이터 update
     */
    fun updateNextWeeksData(currentIndex: Int, nextIndex: Int) {
        val currentWeekMonday: DateOfWeek = weeksData[currentIndex].dateOfWeeks.flatten().first()
        weeksData[nextIndex] = getWeekDates(
            startedMonday = currentWeekMonday.localDate.plusWeeks(1),
        )
        log(
            message = "currentIndex = $currentIndex nextIndex= $nextIndex" +
                "weeksData = ${weeksData[nextIndex].dateOfWeeks.first()}",
        )
    }

    /**
     * 월 캘린더 데이터 초기화
     */
    fun initMonthCalendar(index: Int) {
        val weekData: WeeksData = weeksData[index]
        val localDate: LocalDate = LocalDate.of(
            weekData.year,
            weekData.month,
            1,
        )
        monthData[0] = getMonthDates(monthStart = localDate.minusMonths(1))
        monthData[1] = getMonthDates(monthStart = localDate)
        monthData[2] = getMonthDates(monthStart = localDate.plusMonths(1))

        coroutineScope.launch {
            _calendarDataProviderEventChannel.send(
                element = CalendarDataProviderEvent.CompleteInitMonthCalendar,
            )
        }
    }

    /**
     * 현재 페이지 의 이전 페이지 월 데이터 update
     */
    fun updatePreviousMonthData(currentIndex: Int, prevIndex: Int) {
        val currentMonthLocalDate: LocalDate = getCurrentMonthLocalDate(index = currentIndex)
        monthData[prevIndex] = getMonthDates(monthStart = currentMonthLocalDate.minusMonths(1))
        log(
            message = "currentIndex = $currentIndex prevIndex = $prevIndex" +
                "weeksData = ${monthData[prevIndex].dateOfWeeks.first()}",
        )
    }

    /**
     * 현재 페이지 의 다음 페이지 월 데이터 update
     */
    fun updateNextMonthData(currentIndex: Int, nextIndex: Int) {
        val currentMonthLocalDate: LocalDate = getCurrentMonthLocalDate(index = currentIndex)
        monthData[nextIndex] = getMonthDates(monthStart = currentMonthLocalDate.plusMonths(1))
        log(
            message = "currentIndex = $currentIndex nextIndex = $nextIndex" +
                "weeksData = ${monthData[nextIndex].dateOfWeeks.first()}",
        )
    }

    /**
     * 월의 첫 날이 포함된 주의 월요일부터 마지막 날이 포함된 주의 일요일까지 날짜 리스트 반환
     */
    private fun getMonthDates(monthStart: LocalDate): WeeksData {
        /**
         * 주어진 월의 첫 날
         */
        val firstDayOfMonth: LocalDate = monthStart.with(TemporalAdjusters.firstDayOfMonth())

        /**
         * 주어진 월의 첫 날이 포함된 주의 월요일
         */
        val firstMondayOfWeekContainingFirstDay: LocalDate = firstDayOfMonth.with(
            TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY),
        )

        /**
         * 주어진 월의 마지막 날
         */
        val monthEnd: LocalDate = monthStart.with(TemporalAdjusters.lastDayOfMonth())

        /**
         * 주어진 월의 마지막 날이 포함된 주의 일요일
         */
        val lastSundayOfWeekContainingLastDay: LocalDate = monthEnd.with(
            TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY),
        )

        /**
         * 주어진 월의 첫 날이 포함된 주의 월요일 부터 마지막 날이 포함된 주의 일요일 까지의 일 수
         */
        val daysInPeriod: Long =
            lastSundayOfWeekContainingLastDay.toEpochDay() - firstMondayOfWeekContainingFirstDay.toEpochDay() + 1

        return WeeksData(
            year = monthStart.year,
            month = monthStart.monthValue,
            dateOfWeeks = (0 until daysInPeriod).map { day: Long ->
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
     * [index] 현재 나의 월별 페이지[0,1,2]
     * 월의 시작이 다른 달일 수도 있어 정확한 현재 달의 LocalDate 값을 반환.
     */
    private fun getCurrentMonthLocalDate(index: Int): LocalDate {
        val currentMonth: Int = monthData[index].month
        val currentMonthLocalDateOfWeek: DateOfWeek =
            monthData[index].dateOfWeeks.flatten().first { dateOfWeek: DateOfWeek ->
                dateOfWeek.localDate.monthValue == currentMonth
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
}
