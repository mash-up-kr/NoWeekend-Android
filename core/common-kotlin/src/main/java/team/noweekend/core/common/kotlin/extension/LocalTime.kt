package team.noweekend.core.common.kotlin.extension

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.toJavaLocalDateTime
import kotlinx.datetime.toJavaLocalTime
import kotlinx.datetime.toKotlinLocalDate
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

fun LocalDateTime.toLocalDate(): LocalDate {
    return this.toJavaLocalDateTime().toLocalDate().toKotlinLocalDate()
}


fun LocalTime.plusHours(hours: Int): LocalTime {
    val secondsInDay = 24 * 60 * 60
    val totalSeconds = (this.toSecondOfDay() + hours * 60 * 60) % secondsInDay
    // 만약 음수가 나오면 하루를 더해줌
    val normalizedSeconds = if (totalSeconds < 0) totalSeconds + secondsInDay else totalSeconds
    return LocalTime.fromSecondOfDay(normalizedSeconds)
}

fun LocalTime.toFormattedString(pattern: String): String {
    val javaLocalTime = this.toJavaLocalTime()
    val formatter = DateTimeFormatter.ofPattern(pattern)
        .withZone(ZoneId.systemDefault())
    return javaLocalTime.format(formatter)
}
