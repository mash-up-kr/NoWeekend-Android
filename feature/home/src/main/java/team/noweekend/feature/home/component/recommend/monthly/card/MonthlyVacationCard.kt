package team.noweekend.feature.home.component.recommend.monthly.card

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.datetime.LocalDate
import team.noweekend.core.common.kotlin.extension.MONTH_DATE_WITH_DAY_OF_WEEK_PATTERN
import team.noweekend.core.common.kotlin.extension.now
import team.noweekend.core.common.kotlin.extension.toFormattedString
import team.noweekend.core.design.system.core.component.icon.NWKIcon
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.core.resource.NWKDrawableResource

@Composable
internal fun MonthlyVacationCard(
    date: LocalDate,
    recommendContent: String,
    onClickRecommendedVacation: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                onClick = onClickRecommendedVacation,
            )
            .padding(
                horizontal = NWKTheme.spacing.space175,
                vertical = NWKTheme.spacing.space150,
            ),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(
                text = date.toFormattedString(LocalDate.MONTH_DATE_WITH_DAY_OF_WEEK_PATTERN),
                style = NWKTheme.typography.subTitle1.copy(
                    fontWeight = FontWeight.W500,
                    color = NWKTheme.color.Semantic.Text.body,
                ),
            )
            Text(
                text = recommendContent,
                style = NWKTheme.typography.heading6.copy(
                    fontWeight = FontWeight.W700,
                    color = NWKTheme.color.Semantic.Text.neutral,
                ),
            )
        }

        NWKIcon(
            modifier = Modifier.size(24.dp),
            resourceId = NWKDrawableResource.Plus,
            tint = NWKTheme.color.Semantic.Text.body,
        )
    }
}

@Preview
@Composable
private fun MonthlyVacationCardPreview() {
    NWKTheme {
        MonthlyVacationCard(
            date = LocalDate.now(),
            recommendContent = "오전 8시부터 비가 온데요, 반차 어때요?",
            onClickRecommendedVacation = {},
        )
    }
}
