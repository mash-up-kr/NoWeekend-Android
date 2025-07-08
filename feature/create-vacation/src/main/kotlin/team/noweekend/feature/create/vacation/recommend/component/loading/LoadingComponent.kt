package team.noweekend.feature.create.vacation.recommend.component.loading

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.feature.create.vacation.recommend.component.lottie.LoadingAnimation

@Composable
internal fun BoxScope.LoadingComponent(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.align(Alignment.Center),
        verticalArrangement = Arrangement.spacedBy(NWKTheme.spacing.space100),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "휴가를 굽고 있어요",
            style = NWKTheme.typography.heading4.copy(
                fontWeight = FontWeight.W700,
                color = NWKTheme.color.Semantic.Text.neutral,
            ),
        )
        LoadingAnimation(
            modifier = Modifier.size(width = 200.dp, height = 45.dp),
        )
    }
}
