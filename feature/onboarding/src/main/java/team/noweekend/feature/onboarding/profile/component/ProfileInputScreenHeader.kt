package team.noweekend.feature.onboarding.profile.component

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.core.resource.NWKStringResource

@Composable
internal fun ColumnScope.ProfileInputScreenHeader(
    modifier: Modifier = Modifier,
) {
    Text(
        text = stringResource(NWKStringResource.OnboardProfileInputHeaderTitle),
        style = NWKTheme.typography.heading2.copy(
            fontWeight = FontWeight.W700,
            color = NWKTheme.color.Neutral.neutralGray900,
        ),
    )
    Spacer(modifier = Modifier.size(4.dp))
    Text(
        text = stringResource(NWKStringResource.OnboardProfileInputHeaderSubtitle),
        style = NWKTheme.typography.body1.copy(
            fontWeight = FontWeight.W500,
            color = NWKTheme.color.Semantic.Text.body,
        ),
    )
}
