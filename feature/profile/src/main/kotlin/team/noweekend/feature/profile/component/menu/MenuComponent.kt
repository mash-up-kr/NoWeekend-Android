package team.noweekend.feature.profile.component.menu

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import team.noweekend.core.common.android.extension.fillMaxWidthOfScreen
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.feature.profile.model.Menu


@Composable
fun MenuComponent(
    menu: Menu,
    onClickMenuItem: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidthOfScreen()
            .height(56.dp)
            .clickable(onClick = onClickMenuItem),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            modifier = Modifier.padding(start = 20.dp),
            text = stringResource(id = menu.title),
            style = NWKTheme.typography.body1.copy(
                fontWeight = FontWeight.Medium,
            ),
            color = NWKTheme.color.Semantic.Text.neutral,
        )
        menu.content?.invoke()
    }
}
