package team.noweekend.feature.profile.component.topbar

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import team.noweekend.core.design.system.core.component.icon.NWKIcon
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.core.resource.NWKDrawableResource.ChevronLeft
import team.noweekend.core.resource.NWKStringResource.ProfileInfoEditTitle
import team.noweekend.feature.profile.component.topbar.core.Header
import team.noweekend.feature.profile.component.topbar.core.HeaderAlignmentType

@Composable
internal fun EditInfoTopBar(
    onClickBackButton: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Header(
        modifier = modifier,
        components = arrayOf(
            { componentModifier ->
                NWKIcon(
                    modifier = componentModifier.clickable(onClick = onClickBackButton),
                    resourceId = ChevronLeft,
                )
            },
            { componentModifier ->
                Text(
                    modifier = componentModifier,
                    text = stringResource(id = ProfileInfoEditTitle),
                    style = NWKTheme.typography.heading6,
                    color = NWKTheme.color.Semantic.Text.neutral,
                    textAlign = TextAlign.Center,
                )
            },
        ),
        alignment = HeaderAlignmentType.CenterFill,
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewEditInfoTopBar() {
    NWKTheme {
        EditInfoTopBar(
            onClickBackButton = {}
        )
    }
}
