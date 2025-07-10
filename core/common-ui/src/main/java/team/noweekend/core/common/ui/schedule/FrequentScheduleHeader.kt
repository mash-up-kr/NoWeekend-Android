package team.noweekend.core.common.ui.schedule

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.core.resource.NWKStringResource

@Composable
fun ColumnScope.FrequentScheduleHeader(
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(NWKStringResource.FrequentScheduleHeaderTitle),
        style = NWKTheme.typography.heading2.copy(
            fontWeight = FontWeight.W700,
            color = NWKTheme.color.Neutral.neutralGray900,
        ),
        textAlign = TextAlign.Center,
    )
    Spacer(modifier = Modifier.size(4.dp))
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(NWKStringResource.FrequentScheduleHeaderSubtitle),
        style = NWKTheme.typography.body1.copy(
            fontWeight = FontWeight.W500,
            color = NWKTheme.color.Semantic.Text.body,
        ),
        textAlign = TextAlign.Center,
    )
}
