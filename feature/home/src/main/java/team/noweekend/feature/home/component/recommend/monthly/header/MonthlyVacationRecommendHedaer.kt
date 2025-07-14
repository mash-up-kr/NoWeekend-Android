package team.noweekend.feature.home.component.recommend.monthly.header

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.datetime.LocalDate
import team.noweekend.core.common.kotlin.extension.getWeekOfMonth
import team.noweekend.core.design.system.core.component.icon.NWKIcon
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.core.resource.NWKDrawableResource

@Composable
internal fun ColumnScope.MonthlyVacationRecommendHeader(
    currentMonthWeek: LocalDate,
    currentLocation: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = "${currentMonthWeek.monthNumber}월 ${currentMonthWeek.getWeekOfMonth()} 휴가를 추천드려요",
            style = NWKTheme.typography.heading5.copy(
                fontWeight = FontWeight.W700,
                color = NWKTheme.color.Semantic.Text.neutral,
            ),
        )
        NWKIcon(
            modifier = Modifier.size(24.dp),
            resourceId = NWKDrawableResource.Location,
            tint = NWKTheme.color.Semantic.Text.body,
        )
    }
    Spacer(modifier = Modifier.size(4.dp))
    Text(
        text = currentLocation,
        style = NWKTheme.typography.body2.copy(
            color = NWKTheme.color.Semantic.Text.disabled,
        ),
    )
    Spacer(modifier = Modifier.size(NWKTheme.spacing.space200))
}
