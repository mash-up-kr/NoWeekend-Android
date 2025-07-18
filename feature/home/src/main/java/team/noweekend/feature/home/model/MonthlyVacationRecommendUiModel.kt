package team.noweekend.feature.home.model

import androidx.compose.runtime.Stable
import kotlinx.datetime.LocalDate
import team.noweekend.core.common.kotlin.extension.now
import team.noweekend.core.common.kotlin.extension.parseLocalDateString
import team.noweekend.core.model.vacation.WeatherRecommendVacation

@Stable
data class MonthlyVacationRecommendUiModel(
    val localDate: LocalDate,
    val recommendContent: String,
) {
    companion object {
        val INITIAL_DATA: MonthlyVacationRecommendUiModel = MonthlyVacationRecommendUiModel(
            localDate = LocalDate.now(),
            recommendContent = "",
        )
    }
}

internal fun WeatherRecommendVacation.toUiModel(): MonthlyVacationRecommendUiModel =
    MonthlyVacationRecommendUiModel(
        localDate = LocalDate.parseLocalDateString(this.date),
        recommendContent = content,
    )
