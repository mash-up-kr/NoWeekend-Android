package team.noweekend.feature.home.model

import androidx.compose.runtime.Stable
import kotlinx.datetime.LocalDate
import team.noweekend.core.common.kotlin.extension.parseLocalDateString
import team.noweekend.core.model.vacation.WeatherRecommendVacation
import java.util.UUID

@Stable
data class MonthlyVacationRecommendUiModel(
    val id: String = UUID.randomUUID().toString(),
    val localDate: LocalDate,
    val recommendContent: String,
)

internal fun WeatherRecommendVacation.toUiModel(): MonthlyVacationRecommendUiModel =
    MonthlyVacationRecommendUiModel(
        localDate = LocalDate.parseLocalDateString(this.date),
        recommendContent = content,
    )
