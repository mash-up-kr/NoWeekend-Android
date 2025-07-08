package team.noweekend.feature.profile.component.topbar

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.core.resource.NWKStringResource.ProfileEditButtonTitle
import team.noweekend.feature.profile.component.topbar.core.Header
import team.noweekend.feature.profile.component.topbar.core.HeaderAlignmentType

@Composable
internal fun ProfileTopBar(
    userName: String,
    onClickEditButton: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Header(
        modifier = modifier,
        components = arrayOf(
            { componentModifier ->
                Text(
                    modifier = componentModifier,
                    text = userName,
                    style = NWKTheme.typography.heading4,
                    color = NWKTheme.color.Semantic.Text.neutral,
                )
            },
            { componentModifier ->
                Text(
                    modifier = componentModifier.clickable(onClick = onClickEditButton),
                    text = stringResource(id = ProfileEditButtonTitle),
                    style = NWKTheme.typography.body2,
                    color = NWKTheme.color.Semantic.Text.body,
                )
            },
        ),
        alignment = HeaderAlignmentType.StartFill,
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewProfileTopBar() {
    NWKTheme {
        ProfileTopBar(
            userName = "유저이름",
            onClickEditButton = {}
        )
    }
}
