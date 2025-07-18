package team.noweekend.feature.calendar.model

import androidx.compose.runtime.Immutable
import kotlinx.datetime.LocalDate


@Immutable
data class StableLocalDate(
    val localDate: LocalDate
)
