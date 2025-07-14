package team.noweekend.feature.home.model

import androidx.compose.runtime.Stable
import kotlinx.datetime.LocalDate
import team.noweekend.core.common.kotlin.extension.now
import team.noweekend.core.common.kotlin.extension.parseLocalDateString
import team.noweekend.core.model.holiday.Holiday

@Stable
data class HolidayUiModel(
    val date: LocalDate,
    val holiday: String,
) {
    companion object {
        val INITIAL_DATA: HolidayUiModel = HolidayUiModel(
            date = LocalDate.now(),
            holiday = "현충일",
        )
    }
}

internal fun Holiday.toUiModel() = HolidayUiModel(
    date = LocalDate.parseLocalDateString(this.date),
    holiday = this.holiday,
)
