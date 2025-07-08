package team.noweekend.core.common.kotlin.extension

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.toJavaLocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter


val LocalTime.Companion.MERIDIEM_HOUR_MINUTE_KR_PATTERN
    get() = "(a) HH:mm"

fun LocalDateTime.toFormattedString(pattern: String): String {
    val javaLocalDateTime = this.toJavaLocalDateTime()
    val formatter = DateTimeFormatter.ofPattern(pattern).withZone(
        ZoneId.systemDefault(),
    )
    return javaLocalDateTime.format(formatter)
}

