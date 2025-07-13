package team.noweekend.feature.home.model

import androidx.compose.runtime.Stable
import kotlinx.datetime.LocalDate
import java.util.UUID

@Stable
data class MonthlyVacationRecommendUiModel(
    val id: String = UUID.randomUUID().toString(),
    val localDate: LocalDate,
    val recommendContent: String,
)
