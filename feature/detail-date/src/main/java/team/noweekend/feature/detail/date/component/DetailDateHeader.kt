package team.noweekend.feature.detail.date.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import team.noweekend.core.common.android.extension.fillMaxWidthOfScreen
import team.noweekend.core.design.system.core.component.icon.NWKIcon
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.core.resource.NWKDrawableResource.ChevronLeft

@Composable
fun DetailDateHeader(
    dateTitle: String,
    onClickBackButton: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidthOfScreen()
            .height(56.dp)
            .padding(start = 12.dp, end = 40.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        NWKIcon(
            modifier = Modifier.clickable(onClick = onClickBackButton),
            resourceId = ChevronLeft,
        )
        Text(
            modifier= Modifier.weight(1f),
            text = dateTitle,
            style = NWKTheme.typography.heading6,
            color = NWKTheme.color.Semantic.Text.neutral,
            textAlign = TextAlign.Center,
        )
    }
}
