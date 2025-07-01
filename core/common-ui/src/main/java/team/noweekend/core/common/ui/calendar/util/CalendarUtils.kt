package team.noweekend.core.common.ui.calendar.util

import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
import kotlinx.datetime.minus
import kotlinx.datetime.number
import kotlinx.datetime.plus
import team.noweekend.core.common.kotlin.extension.instant
import team.noweekend.core.common.kotlin.extension.toLocalDate
import team.noweekend.core.common.kotlin.extension.toLocalDateTime

object CalendarUtils {

    val currentLocalDate = instant.toLocalDate()
    val currentLocalDateTime = instant.toLocalDateTime()

    fun LocalDate.minusWeeks(week: Int): LocalDate {
        return this.minus(week, DateTimeUnit.WEEK)
    }

    fun LocalDate.plusWeeks(week: Int): LocalDate {
        return this.plus(week, DateTimeUnit.WEEK)
    }

    fun LocalDate.minusMonths(month: Int): LocalDate {
        return this.minus(month, DateTimeUnit.MONTH)
    }

    fun LocalDate.plusMonths(month: Int): LocalDate {
        return this.plus(month, DateTimeUnit.MONTH)
    }

    fun LocalDate.plusDays(days: Int): LocalDate {
        return this.plus(days, DateTimeUnit.DAY)
    }

    fun LocalDate.firstDayOfMonth(): LocalDate {
        return LocalDate(this.year, this.month, 1)
    }

    fun LocalDate.lastDayOfMonth(): LocalDate {
        val lastDay = this.month.number.monthLength(isLeapYear(year))
        return LocalDate(this.year, this.month, lastDay)
    }

    fun LocalDate.previousOrSame(dayOfWeek: DayOfWeek): LocalDate {
        val daysDiff = (this.dayOfWeek.value - dayOfWeek.value + 7) % 7
        return this.minus(daysDiff.toLong(), DateTimeUnit.DAY)
    }

    fun LocalDate.nextOrSame(dayOfWeek: DayOfWeek): LocalDate {
        val daysDiff = (dayOfWeek.value - this.dayOfWeek.value + 7) % 7
        return this.plus(daysDiff.toLong(), DateTimeUnit.DAY)
    }

    private fun Int.monthLength(isLeapYear: Boolean): Int {
        return Month.of(this).length(isLeapYear)
    }

    private fun isLeapYear(year: Int): Boolean {
        val prolepticYear = year.toLong()
        return prolepticYear % 4 == 0L && (prolepticYear % 100 != 0L || prolepticYear % 400 == 0L)
    }

    /**
     * 주어진 연도와 월에 따라 해당 월의 일수를 계산
     * @param year 연도
     * @param month 월
     * @return 해당 월의 일수 (28, 29, 30, 또는 31)
     */
    fun getDaysInMonth(year: Int, month: Int): Int {
        // 윤년 계산: 4로 나누어 떨어지고, 100으로 나누어 떨어지지 않거나 400으로 나누어 떨어지는 경우

        return when (Month.of(month)) {
            Month.FEBRUARY -> if (isLeapYear(year)) 29 else 28
            Month.APRIL, Month.JUNE, Month.SEPTEMBER, Month.NOVEMBER -> 30
            else -> 31
        }
    }
}
