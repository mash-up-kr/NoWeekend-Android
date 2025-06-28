package team.noweekend.core.design.system.core.component.card

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.noweekend.core.design.system.core.component.icon.NWKIcon
import team.noweekend.core.design.system.core.component.image.NWKImage
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.core.resource.NWKDrawableResource
import team.noweekend.core.resource.NWKStringResource

@Composable
fun NWKShortCard(
    date: String,
    description: AnnotatedString,
    @DrawableRes drawableResId: Int,
    onCardClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    Column(
        modifier = modifier
            .width(160.dp)
            .border(
                width = 1.dp,
                color = NWKTheme.color.Semantic.Border.border01,
                shape = NWKTheme.radius.borderRadius500,
            )
            .clip(NWKTheme.radius.borderRadius500)
            .clickable(
                enabled = enabled,
                onClick = onCardClick,
            )
            .background(
                color = NWKTheme.color.Semantic.Background.normal,
                shape = NWKTheme.radius.borderRadius500,
            )
            .padding(
                horizontal = NWKTheme.spacing.space175,
                vertical = NWKTheme.spacing.space300,
            ),
        content = {
            NWKShortCardContent(
                drawableResId = drawableResId,
                date = date,
                description = description,
            )
        },
    )
}

@Composable
private fun ColumnScope.NWKShortCardContent(
    @DrawableRes drawableResId: Int,
    date: String,
    description: AnnotatedString,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        NWKImage(
            modifier = Modifier.size(62.dp),
            drawableResId = drawableResId,
        )
        NWKIcon(
            modifier = Modifier.size(24.dp),
            resourceId = NWKDrawableResource.Plus,
            tint = NWKTheme.color.Neutral.neutralGray700,
        )
    }
    Spacer(Modifier.size(NWKTheme.spacing.space175))
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = date,
        style = NWKTheme.typography.subTitle1.copy(
            color = NWKTheme.color.Semantic.Text.body,
            fontWeight = FontWeight.W500,
        ),
    )
    Spacer(Modifier.size(NWKTheme.spacing.space50))
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = description,
        style = NWKTheme.typography.heading6.copy(
            color = NWKTheme.color.Semantic.Text.neutral,
            fontWeight = FontWeight.W700,
        ),
        maxLines = 2,
//        overflow = TextOverflow.Visible,
    )
}

@Preview
@Composable
private fun NWKShortCardPreview() {
    NWKTheme {
        NWKShortCard(
            drawableResId = NWKDrawableResource.Cake,
            date = "0/00(월) ~ 0/00(월)",
            description = buildAnnotatedString {
                append(stringResource(NWKStringResource.Week))
                withStyle(style = SpanStyle(NWKTheme.color.Toast.toast500)) {
                    append(stringResource(NWKStringResource.Friday))
                }
                append(stringResource(NWKStringResource.Sunday))
            },
            onCardClick = {},
        )
    }
}
