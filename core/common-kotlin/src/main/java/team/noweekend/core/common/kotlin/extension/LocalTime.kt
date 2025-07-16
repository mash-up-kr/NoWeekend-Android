package team.noweekend.core.common.kotlin.extension

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.toJavaLocalDateTime
import kotlinx.datetime.toJavaLocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

val LocalTime.Companion.MERIDIEM_HOUR_MINUTE_KR_PATTERN
    get() = "(a) HH:mm"

val LocalTime.Companion.KOREAN_MERIDIEM_HOUR_MINUTE_PATTERN: String
    get() = "a h:mm"

fun LocalDateTime.toFormattedString(pattern: String): String {
    val javaLocalDateTime = this.toJavaLocalDateTime()
    val formatter = DateTimeFormatter.ofPattern(pattern).withZone(
        ZoneId.systemDefault(),
    )
    return javaLocalDateTime.format(formatter)
}

fun LocalTime.toFormattedString(pattern: String): String {
    val javaLocalTime = this.toJavaLocalTime()
    val formatter = DateTimeFormatter.ofPattern(pattern)
        .withZone(ZoneId.systemDefault())
    return javaLocalTime.format(formatter)
}
