package team.noweekend.core.common.kotlin.extension

import kotlinx.datetime.LocalDate
import kotlinx.datetime.toJavaLocalDate
import java.time.format.DateTimeFormatter

val LocalDate.Companion.DATE_WITH_DAY_OF_WEEK_PATTERN
    get() = "M.dd(EE)"

fun LocalDate.toFormattedString(pattern: String): String {
    return this.toJavaLocalDate().format(DateTimeFormatter.ofPattern(pattern))
}
