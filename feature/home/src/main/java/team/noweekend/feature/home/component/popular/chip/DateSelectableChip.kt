package team.noweekend.feature.home.component.popular.chip

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.core.resource.NWKDrawableResource

@Composable
internal fun DateSelectableChip(
    onChipClick: () -> Unit,
    modifier: Modifier = Modifier,
    date: String = "2025년 6월",  // TODO (JaesungLeee) : 추후 LocalDate로 수정
) {
    Row(
        modifier = modifier
            .clip(NWKTheme.radius.borderRadius400)
            .clickable(onClick = onChipClick)
            .background(
                color = NWKTheme.color.Toast.toast500,
                shape = NWKTheme.radius.borderRadius400,
            )
            .padding(
                horizontal = NWKTheme.spacing.space150,
                vertical = NWKTheme.spacing.space100,
            ),
        horizontalArrangement = Arrangement.spacedBy(NWKTheme.spacing.space50),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = date,
            style = NWKTheme.typography.subTitle1.copy(
                color = NWKTheme.color.Neutral.white,
            ),
        )
        Icon(
            modifier = Modifier.size(16.dp),
            painter = painterResource(NWKDrawableResource.ChevronDown),
            tint = NWKTheme.color.Neutral.white,
            contentDescription = null,
        )
    }
}

@Preview
@Composable
private fun DateSelectableChipPreview() {
    NWKTheme {
        DateSelectableChip(
            onChipClick = {},
        )
    }
}
