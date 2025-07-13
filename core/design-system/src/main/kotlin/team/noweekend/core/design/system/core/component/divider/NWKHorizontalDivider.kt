package team.noweekend.core.design.system.core.component.divider

import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import team.noweekend.core.common.android.extension.fillMaxWidthOfScreen
import team.noweekend.core.design.system.foundation.theme.NWKTheme

@Composable
fun NWKHorizontalDivider(
    modifier: Modifier = Modifier,
    thickness: Dp = 8.dp,
    shouldFillScreenWidth: Boolean = false,
) {
    HorizontalDivider(
        thickness = thickness,
        color = NWKTheme.color.Neutral.neutralGray100,
        modifier = if (shouldFillScreenWidth) {
            Modifier.fillMaxWidthOfScreen()
        } else {
            modifier
        },
    )
}
