package team.noweekend.core.common.ui.fab.core

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.noweekend.core.design.system.core.component.icon.NWKIcon
import team.noweekend.core.design.system.core.component.image.NWKImage
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.core.resource.NWKDrawableResource.Close
import team.noweekend.core.resource.NWKDrawableResource.Plus
import team.noweekend.core.resource.NWKStringResource.TodoAddTitle

@Composable
internal fun FabTodoAddButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isExpanded: Boolean = false,
) {
    FloatingActionButton(
        modifier = modifier.padding(1.dp),
        onClick = onClick,
        containerColor = if (isExpanded) {
            NWKTheme.color.Neutral.white
        } else {
            NWKTheme.color.Neutral.black
        },
        shape = if (isExpanded) CircleShape else NWKTheme.radius.borderRadius700,
    ) {
        val animatedWidth = animateDpAsState(
            targetValue = if (isExpanded) 48.dp else 127.dp,
        )

        Row(
            modifier = Modifier
                .width(animatedWidth.value)
                .height(48.dp)
                .padding(vertical = 12.dp, horizontal = if (isExpanded) 12.dp else 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            if (isExpanded) {
                NWKImage(
                    modifier = Modifier.size(24.dp),
                    drawableResId = Close,
                    colorFilter = ColorFilter.tint(color = NWKTheme.color.Neutral.black),
                )
            } else {
                NWKIcon(
                    resourceId = Plus,
                    tint = NWKTheme.color.Neutral.white,
                )
                Text(
                    text = stringResource(id = TodoAddTitle),
                    color = NWKTheme.color.Neutral.white,
                    style = NWKTheme.typography.heading6.copy(
                        fontWeight = FontWeight.Bold,
                    ),
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewFabTodoAddButton() {
    NWKTheme {
        var isExpanded by remember { mutableStateOf(false) }
        FabTodoAddButton(
            isExpanded = isExpanded,
            onClick = {
                isExpanded = isExpanded.not()
            },
        )
    }
}
