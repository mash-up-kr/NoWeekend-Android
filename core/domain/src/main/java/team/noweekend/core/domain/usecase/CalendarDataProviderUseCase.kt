package team.noweekend.core.domain.usecase

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.plus
import team.noweekend.core.common.kotlin.extension.CalendarUtils.currentLocalDate
import team.noweekend.core.common.kotlin.extension.CalendarUtils.firstDayOfMonth
import team.noweekend.core.common.kotlin.extension.CalendarUtils.lastDayOfMonth
import team.noweekend.core.common.kotlin.extension.CalendarUtils.minusMonths
import team.noweekend.core.common.kotlin.extension.CalendarUtils.minusWeeks
import team.noweekend.core.common.kotlin.extension.CalendarUtils.nextOrSame
import team.noweekend.core.common.kotlin.extension.CalendarUtils.plusDays
import team.noweekend.core.common.kotlin.extension.CalendarUtils.plusMonths
import team.noweekend.core.common.kotlin.extension.CalendarUtils.plusWeeks
import team.noweekend.core.common.kotlin.extension.CalendarUtils.previousOrSame
import team.noweekend.core.common.kotlin.extension.YEAR_MONTH_DAY_PATTERN
import team.noweekend.core.common.kotlin.extension.now
import team.noweekend.core.common.kotlin.extension.parseLocalDateString
import team.noweekend.core.common.kotlin.extension.toFormattedString
import team.noweekend.core.domain.repository.ScheduleRepository
import team.noweekend.core.model.calendar.DateOfWeek
import team.noweekend.core.model.calendar.WeeksData
import team.noweekend.core.model.calendar.getImageType
import team.noweekend.core.model.schedule.Schedule
import team.noweekend.core.model.schedule.ScheduleCategory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CalendarDataProviderUseCase @Inject constructor(
    private val scheduleRepository: ScheduleRepository,
) {
    private var _targetDate: MutableStateFlow<LocalDate> = MutableStateFlow(LocalDate.now())
    val targetDate: StateFlow<LocalDate> = _targetDate.asStateFlow()

    private val _calendarDataProviderEventChannel: Channel<CalendarDataProviderEvent> =
        Channel(capacity = Channel.BUFFERED)
    val calendarDataProviderEventFlow: Flow<CalendarDataProviderEvent> =
        _calendarDataProviderEventChannel.receiveAsFlow()

    private val _weeksData: MutableStateFlow<Map<Int, WeeksData>> = MutableStateFlow(mapOf())
    val weeksDate: StateFlow<Map<Int, WeeksData>> = _weeksData.asStateFlow()
    private val _monthData: MutableStateFlow<Map<Int, WeeksData>> = MutableStateFlow(mapOf())
    val monthData: StateFlow<Map<Int, WeeksData>> = _monthData.asStateFlow()

    private suspend fun getWeekDates(startedMonday: LocalDate, month: Int = startedMonday.monthNumber): WeeksData {
        val startDate = startedMonday.toFormattedString(LocalDate.YEAR_MONTH_DAY_PATTERN)
        val endDate = startedMonday.plusDays(6).toFormattedString(LocalDate.YEAR_MONTH_DAY_PATTERN)

        val scheduleList = scheduleRepository.getSchedule(startDate = startDate, endDate = endDate)
        val currentDate = LocalDate.now()

        return WeeksData(
            year = startedMonday.year,
            month = month,
            dateOfWeeks = scheduleList.map { dateWithSchedules ->
                val parsingDate = LocalDate.parseLocalDateString(isoString = dateWithSchedules.date)
                val isFuture = parsingDate > currentDate
                val hasRest =
                    dateWithSchedules.schedules.any { schedule -> schedule.category == ScheduleCategory.LEAVE }
                val dateOfWeek = DateOfWeek(
                    localDate = parsingDate,
                    isCurrentDate = parsingDate == currentDate,
                    imageType = getImageType(
                        temperature = dateWithSchedules.dailyTemperature,
                        isFuture = isFuture,
                        hasRest = hasRest,
                        hasSchedule = dateWithSchedules.schedules.isNotEmpty(),
                    ),
                    scheduleList = dateWithSchedules.schedules,
                )
                dateOfWeek
            }.chunked(7),
        )
    }

    private suspend fun getWeekDates(startedMonday: LocalDate): WeeksData {
        return getWeekDates(startedMonday = startedMonday, month = startedMonday.monthNumber)
    }

    suspend fun initWeekCalendar(initPage: Int, currentDate: LocalDate = LocalDate.now()) {
        _targetDate.update { currentDate }
        val targetWeekMonday: LocalDate = currentDate.previousOrSame(DayOfWeek.MONDAY)

        val newMap = mapOf(
            initPage - 1 to getWeekDates(targetWeekMonday.minusWeeks(1)),
            initPage to getWeekDates(targetWeekMonday),
            initPage + 1 to getWeekDates(targetWeekMonday.plusWeeks(1)),
        )
        _weeksData.update {
            newMap.toMutableMap()
        }

        _calendarDataProviderEventChannel.send(
            element = CalendarDataProviderEvent.CompleteInitWeeksCalendar,
        )
    }

    suspend fun updatePreviousWeeksData(currentPage: Int) {
        val offsetPageData =
            _weeksData.value[currentPage] ?: throw IllegalStateException("offsetPageData $currentPage is null")
        val currentWeekMonday: DateOfWeek = offsetPageData.dateOfWeeks.flatten().first()

        val prevPage = currentPage - 1

        val copiedMap = _weeksData.value.toMutableMap()
        copiedMap[prevPage] = getWeekDates(startedMonday = currentWeekMonday.localDate.minusWeeks(1))
        val removeNeededPage = currentPage + 2
        copiedMap.remove(removeNeededPage)

        _weeksData.update {
            copiedMap
        }
    }

    /**
     * 현재 페이지 의 다음 페이지 주 데이터 update
     */
    suspend fun updateNextWeeksData(currentPage: Int) {
        val offsetPageData = _weeksData.value[currentPage] ?: throw IllegalStateException("offsetPageData is null")
        val currentWeekMonday: DateOfWeek = offsetPageData.dateOfWeeks.flatten().first()

        val nextPageOffset = currentPage + 1

        val copiedMap = _weeksData.value.toMutableMap()

        copiedMap[nextPageOffset] = getWeekDates(startedMonday = currentWeekMonday.localDate.plusWeeks(1))

        val removeNeededPage = currentPage - 2
        copiedMap.remove(removeNeededPage)

        _weeksData.update {
            copiedMap
        }
    }

    /**
     * 월 캘린더 데이터 초기화
     */
    suspend fun initMonthCalendar(page: Int, chooserMonth: LocalDate) {
        val newMap = mapOf(
            page to getMonthDates(monthStart = chooserMonth),
            page - 1 to getMonthDates(monthStart = chooserMonth.minusMonths(1)),
            page + 1 to getMonthDates(monthStart = chooserMonth.plusMonths(1)),
        )

        _monthData.update { newMap }

        _calendarDataProviderEventChannel.send(
            element = CalendarDataProviderEvent.CompleteInitMonthCalendar,
        )
    }

    /**
     * 현재 페이지 의 이전 페이지 월 데이터 update
     */
    suspend fun updatePreviousMonthData(currentPage: Int) {
        val currentMonthLocalDate: LocalDate = getCurrentMonthLocalDate(currentPage = currentPage)
        val prevPage = currentPage - 1

        val copiedMap = _monthData.value.toMutableMap()
        copiedMap[prevPage] = getMonthDates(monthStart = currentMonthLocalDate.minusMonths(1))

        val removeNeededPage = currentPage + 2

        copiedMap.remove(removeNeededPage)
        _monthData.update { copiedMap }
    }

    /**
     * 현재 페이지 의 다음 페이지 월 데이터 update
     */
    suspend fun updateNextMonthData(currentPage: Int) {
        val currentMonthLocalDate: LocalDate = getCurrentMonthLocalDate(currentPage = currentPage)
        val nextPage = currentPage + 1

        val copiedMap = _monthData.value.toMutableMap()

        copiedMap[nextPage] = getMonthDates(monthStart = currentMonthLocalDate.plusMonths(1))

        val removeNeededPage = currentPage - 2

        copiedMap.remove(removeNeededPage)

        _monthData.update {
            copiedMap
        }
    }

    /**
     * 월의 첫 날이 포함된 주의 월요일부터 마지막 날이 포함된 주의 일요일까지 날짜 리스트 반환
     */
    private suspend fun getMonthDates(monthStart: LocalDate): WeeksData {
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

        val startDate = firstMondayOfWeekContainingFirstDay.toFormattedString(LocalDate.YEAR_MONTH_DAY_PATTERN)
        val endDate = firstMondayOfWeekContainingFirstDay.plusDays(daysInPeriod - 1)
            .toFormattedString(LocalDate.YEAR_MONTH_DAY_PATTERN)

        val scheduleList = scheduleRepository.getSchedule(startDate = startDate, endDate = endDate)
        val currentDate = LocalDate.now()

        return WeeksData(
            year = monthStart.year,
            month = monthStart.monthNumber,
            dateOfWeeks = scheduleList.map { dateWithSchedules ->
                val parsingDate = LocalDate.parseLocalDateString(isoString = dateWithSchedules.date)
                val isFuture = parsingDate > currentDate
                val hasRest =
                    dateWithSchedules.schedules.any { schedule -> schedule.category == ScheduleCategory.LEAVE }

                DateOfWeek(
                    localDate = parsingDate,
                    isCurrentDate = parsingDate == currentDate,
                    imageType = getImageType(
                        temperature = dateWithSchedules.dailyTemperature,
                        isFuture = isFuture,
                        hasRest = hasRest,
                        hasSchedule = dateWithSchedules.schedules.isNotEmpty(),
                    ),
                    scheduleList = dateWithSchedules.schedules,
                )
            }.chunked(7),
        )
    }

    /**
     * [currentPage] pagerState의 currentPage
     * 월의 시작이 다른 달일 수도 있어 정확한 현재 달의 LocalDate 값을 반환.
     */
    private fun getCurrentMonthLocalDate(currentPage: Int): LocalDate {
        val currentMonthData: WeeksData =
            _monthData.value[currentPage] ?: throw IllegalStateException("currentPage is null")
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
    fun updateTargetDate(localDate: LocalDate) {
        _targetDate.value = localDate
    }

    fun getWeeksData(page: Int): WeeksData? = _weeksData.value[page]
    fun getMonthData(page: Int): WeeksData? = _monthData.value[page]

    fun updateWeeksDataWithSchedule(schedule: Schedule) {
        _weeksData.update { weeksDataMap ->
            weeksDataMap.mapValues { entry ->
                entry.value.copy(
                    dateOfWeeks = entry.value.dateOfWeeks.map { dateOfWeekList ->
                        dateOfWeekList.map { dateOfWeek ->
                            val scheduleList = dateOfWeek.scheduleList
                            val updatedScheduleList = scheduleList.map { innerSchedule ->
                                if (schedule.id == innerSchedule.id) schedule else innerSchedule
                            }
                            val imageType = updatedScheduleList.getImageType(
                                dateOfWeek = dateOfWeek,
                                localDate = currentLocalDate,
                            )
                            dateOfWeek.copy(
                                scheduleList = updatedScheduleList,
                                imageType = imageType,
                            )
                        }
                    },
                )
            }
        }
    }

    fun updateMonthsDataWithSchedule(schedule: Schedule) {
        _monthData.update { weeksDataMap ->
            weeksDataMap.mapValues { entry ->
                entry.value.copy(
                    dateOfWeeks = entry.value.dateOfWeeks.map { dateOfWeekList ->
                        dateOfWeekList.map { dateOfWeek ->

                            val updateScheduleList = dateOfWeek.scheduleList.map { innerSchedule ->
                                if (schedule.id == innerSchedule.id) schedule else innerSchedule
                            }

                            dateOfWeek.copy(
                                scheduleList = updateScheduleList,
                                imageType = updateScheduleList.getImageType(
                                    localDate = currentLocalDate,
                                    dateOfWeek = dateOfWeek,
                                ),
                            )
                        }
                    },
                )
            }
        }
    }

    sealed interface CalendarDataProviderEvent {
        data object CompleteInitWeeksCalendar : CalendarDataProviderEvent
        data object CompleteInitMonthCalendar : CalendarDataProviderEvent
    }
}
