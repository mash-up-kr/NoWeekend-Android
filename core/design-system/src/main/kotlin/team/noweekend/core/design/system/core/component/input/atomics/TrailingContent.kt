package team.noweekend.core.design.system.core.component.input.atomics

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.clearText
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import team.noweekend.core.design.system.core.component.icon.NWKIcon
import team.noweekend.core.design.system.core.component.input.status.TextInputType
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.core.resource.NWKDrawableResource
import team.noweekend.core.resource.NWKStringResource

@Composable
internal fun TrailingContent(
    textFieldState: TextFieldState,
    textInputType: TextInputType,
    modifier: Modifier = Modifier,
) {
    when (textInputType) {
        TextInputType.NICKNAME -> {
            if (textFieldState.text.isNotEmpty()) {
                NWKIcon(
                    modifier = modifier
                        .size(24.dp)
                        .clickable { textFieldState.clearText() },
                    resourceId = NWKDrawableResource.TextClear,
                    tint = NWKTheme.color.Neutral.neutralGray400,
                )
            }
        }

        TextInputType.DAY -> {
            Text(
                modifier = modifier,
                text = stringResource(NWKStringResource.Day),
                color = NWKTheme.color.Semantic.Text.neutral,
                style = NWKTheme.typography.body1,
            )
        }

        TextInputType.TIME -> {
            Text(
                modifier = modifier,
                text = stringResource(NWKStringResource.Time),
                color = NWKTheme.color.Semantic.Text.neutral,
                style = NWKTheme.typography.body1,
            )
        }

        else -> {}
    }
}
