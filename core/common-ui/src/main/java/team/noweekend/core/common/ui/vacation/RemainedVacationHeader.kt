package team.noweekend.core.common.ui.vacation

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
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.core.resource.NWKStringResource

@Composable
fun ColumnScope.RemainedVacationHeader(
    days: Int,
    hours: Int,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(NWKStringResource.RemainedVacationHeaderTitle),
        style = NWKTheme.typography.heading2.copy(
            fontWeight = FontWeight.W700,
            color = NWKTheme.color.Neutral.neutralGray900,
        ),
        textAlign = TextAlign.Center
    )
    Spacer(modifier = Modifier.size(NWKTheme.spacing.space400))
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(NWKStringResource.RemainedVacationHeaderSubtitle, days, hours),
        style = NWKTheme.typography.heading2.copy(
            fontWeight = FontWeight.W700,
            color = NWKTheme.color.Toast.toast500,
        ),
        textAlign = TextAlign.Center,
    )
}
