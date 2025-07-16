package team.noweekend.core.common.kotlin.extension

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

val instant = Clock.System.now()

fun Instant.toLocalDateTime(): LocalDateTime = toLocalDateTime(TimeZone.currentSystemDefault())
fun Instant.toLocalDate(): LocalDate = this.toLocalDateTime().date
