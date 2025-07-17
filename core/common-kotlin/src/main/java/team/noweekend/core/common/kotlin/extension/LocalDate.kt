package team.noweekend.core.common.kotlin.extension

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.datetime.toJavaLocalDate
import kotlinx.datetime.toJavaLocalTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.time.temporal.WeekFields
import java.util.Locale

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

fun LocalDate.getWeekOfMonth(): String {
    val javaLocalDate = this.toJavaLocalDate()
    val weekFields = WeekFields.of(Locale.getDefault())
    return javaLocalDate.get(weekFields.weekOfMonth()).toKrPattern()
}

fun LocalDate.toDateTimeString(
    time: LocalTime,
    pattern: String = "yyyy-MM-dd'T'HH:mm:ss",
): String {
    val javaDateTime = java.time.LocalDateTime.of(this.toJavaLocalDate(), time.toJavaLocalTime())
    val formatter = DateTimeFormatter.ofPattern(pattern)
    return javaDateTime.format(formatter)
}

private fun Int.toKrPattern(): String {
    return when (this) {
        1 -> "첫째주"
        2 -> "둘째주"
        3 -> "셋째주"
        4 -> "넷째주"
        5 -> "다섯째주"
        else -> "${this}주차"
    }
}

