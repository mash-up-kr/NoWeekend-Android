package team.noweekend.feature.home.component.holiday.card

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.datetime.LocalDate
import team.noweekend.core.common.kotlin.extension.DATE_WITH_DAY_OF_WEEK_PATTERN
import team.noweekend.core.common.kotlin.extension.toFormattedString
import team.noweekend.core.design.system.core.component.card.NWKHolidayCard
import team.noweekend.core.design.system.foundation.theme.NWKTheme

@Composable
internal fun HolidayCard(
    date: LocalDate,
    holiday: String,
    onCardClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    NWKHolidayCard(
        modifier = modifier,
        onCardClick = onCardClick,
        title = date.toFormattedString(LocalDate.DATE_WITH_DAY_OF_WEEK_PATTERN),
        description = holiday,
        enabled = enabled,
    )
}

@Preview
@Composable
private fun HolidayCardPreview() {
    NWKTheme {
        HolidayCard(
            onCardClick = {},
            date = LocalDate(2025, 6, 6),
            holiday = "현충일",
        )
    }
}
