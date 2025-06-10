package team.noweekend.core.design.system.core.component.divider

import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import team.noweekend.core.common.android.extension.fillMaxWidthOfScreen
import team.noweekend.core.design.system.foundation.theme.NWKTheme

@Composable
fun NWKHorizontalDivider(
    shouldFillScreenWidth: Boolean,
    modifier: Modifier = Modifier,
) {
    HorizontalDivider(
        thickness = 8.dp,
        color = NWKTheme.color.Neutral.neutralGray100,
        modifier = if (shouldFillScreenWidth) {
            Modifier.fillMaxWidthOfScreen()
        } else {
            modifier
        },
    )
}
