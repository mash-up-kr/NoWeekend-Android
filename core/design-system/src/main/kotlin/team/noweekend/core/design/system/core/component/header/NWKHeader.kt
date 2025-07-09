package team.noweekend.core.design.system.core.component.header

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.noweekend.core.design.system.core.component.image.NWKImage
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.core.resource.NWKDrawableResource

@Composable
fun NWKHeader(
    onClickBack: () -> Unit,
    modifier: Modifier = Modifier,
    title: String? = null,
) {
    Box(
        modifier = modifier
            .background(NWKTheme.color.Semantic.Background.normal)
            .padding(horizontal = 12.dp, vertical = 16.dp),
    ) {
        BackButton(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .size(26.dp),
            onClick = onClickBack,
        )
        HeaderTitle(
            modifier = Modifier.align(Alignment.Center),
            title = title,
        )
    }
}

@Composable
private fun BackButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    NWKImage(
        modifier = modifier.clickable { onClick() },
        drawableResId = NWKDrawableResource.ChevronLeft,
    )
}

@Composable
private fun HeaderTitle(
    title: String?,
    modifier: Modifier = Modifier,
) {
    title?.let { text ->
        Text(
            modifier = modifier,
            text = text,
            color = NWKTheme.color.Semantic.Text.neutral,
            style = NWKTheme.typography.heading6,
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun NWKHeaderPreview() {
    NWKHeader(
        onClickBack = {},
        title = "Header Title",
        modifier = Modifier.fillMaxWidth(),
    )
}
