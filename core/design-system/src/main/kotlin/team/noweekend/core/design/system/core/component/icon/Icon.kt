package team.noweekend.core.design.system.core.component.icon

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.core.resource.NWKDrawableResource

@Composable
fun NWKIcon(
    @DrawableRes resourceId: Int,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    tint: Color = LocalContentColor.current,
) {
    Icon(
        painter = rememberVectorPainter(ImageVector.vectorResource(id = resourceId)),
        contentDescription = contentDescription,
        modifier = modifier,
        tint = tint,
    )
}

@Preview
@Composable
private fun NWKIconPreview() {
    NWKTheme {
        Row(
            modifier = Modifier
                .background(NWKTheme.color.Neutral.white),
        ) {
            NWKIcon(
                resourceId = NWKDrawableResource.HomeOn,
                modifier = Modifier.size(24.dp),
                tint = NWKTheme.color.Neutral.neutralGray700,
            )
            NWKIcon(
                resourceId = NWKDrawableResource.PersonOn,
                modifier = Modifier.size(24.dp),
                tint = NWKTheme.color.Neutral.neutralGray700,
            )
            NWKIcon(
                resourceId = NWKDrawableResource.CalendarOn,
                modifier = Modifier.size(24.dp),
                tint = NWKTheme.color.Neutral.neutralGray700,
            )
            NWKIcon(
                resourceId = NWKDrawableResource.HomeOn,
                modifier = Modifier.size(24.dp),
            )
            NWKIcon(
                resourceId = NWKDrawableResource.PersonOn,
                modifier = Modifier.size(24.dp),
            )
            NWKIcon(
                resourceId = NWKDrawableResource.CalendarOn,
                modifier = Modifier.size(24.dp),
            )
        }
    }
}
