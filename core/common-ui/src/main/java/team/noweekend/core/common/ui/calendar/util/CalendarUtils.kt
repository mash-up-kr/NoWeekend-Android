package team.noweekend.core.common.ui.calendar.util

import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.number
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime

object CalendarUtils {

    fun now(): LocalDate {
        return Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
    }

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
}
