package team.noweekend.core.common.kotlin.extension

import kotlinx.datetime.LocalDate
import kotlinx.datetime.toJavaLocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

val LocalDate.Companion.YEAR_MONTH_DATE_WITH_DAY_OF_WEEK_PATTERN
    get() = "yyyy.M.dd (EE)"
val LocalDate.Companion.YEAR_MONTH_KR_PATTERN
    get() = "yyyy년 MM월"

val LocalDate.Companion.MONTH_DATE_WITH_DAY_OF_WEEK_KR_PATTERN
    get() = "M월 dd일 (EE)"

val LocalDate.Companion.MONTH_DATE_WITH_DAY_OF_WEEK_PATTERN
    get() = "M/dd (EE)"

val LocalDate.Companion.MONTH_DATE_PATTERN
    get() = "M/dd"

val LocalDate.Companion.YEAR_MONTH_DAY_PATTERN
    get() = "yyyy-MM-dd"

fun LocalDate.toFormattedString(pattern: String): String {
    return this.toJavaLocalDate().format(DateTimeFormatter.ofPattern(pattern))
}

fun LocalDate.Companion.now(): LocalDate = instant.toLocalDate()

fun LocalDate.Companion.parseLocalDateString(isoString: String): LocalDate {
    return try {
        parse(isoString)
    } catch (e: DateTimeParseException) {
        e.printStackTrace()
        LocalDate.now()
    }
}
