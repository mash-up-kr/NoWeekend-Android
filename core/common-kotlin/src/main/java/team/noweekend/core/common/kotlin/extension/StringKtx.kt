import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime

fun String.toLocalDate(): LocalDate =
    LocalDateTime.parse(this).date

fun String.toLocalTime(): LocalTime =
    LocalDateTime.parse(this).time
