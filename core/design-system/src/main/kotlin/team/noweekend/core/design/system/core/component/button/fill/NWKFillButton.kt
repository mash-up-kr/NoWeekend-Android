package team.noweekend.core.design.system.core.component.button.fill

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import team.noweekend.core.design.system.core.component.button.defaults.BoxButtonType
import team.noweekend.core.design.system.core.component.button.defaults.Button
import team.noweekend.core.design.system.core.component.button.defaults.ButtonSizeType
import team.noweekend.core.design.system.core.component.button.defaults.NWKButtonDefaults
import team.noweekend.core.design.system.core.component.scaffold.NWKScaffold
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.core.resource.NWKDrawableResource

@Composable
fun NWKFillButton(
    onClick: () -> Unit,
    text: String,
    type: BoxButtonType,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    val colors = when (type) {
        BoxButtonType.PRIMARY -> NWKButtonDefaults.primaryButtonColors()
        BoxButtonType.BLACK -> NWKButtonDefaults.blackButtonColors()
    }

    Button(
        onClick = onClick,
        colors = colors,
        size = ButtonSizeType.EXTRA_LARGE,
        modifier = modifier,
        enabled = enabled,
        content = {
            Text(
                modifier = Modifier,
                text = text,
                style = NWKTheme.typography.heading6.copy(
                    color = NWKTheme.color.Neutral.white,
                ),
                maxLines = 1,
            )
        },
    )
}

@Composable
fun NWKFillButton(
    onClick: () -> Unit,
    type: BoxButtonType,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit,
) {
    val colors = when (type) {
        BoxButtonType.PRIMARY -> NWKButtonDefaults.primaryButtonColors()
        BoxButtonType.BLACK -> NWKButtonDefaults.blackButtonColors()
    }

    Button(
        onClick = onClick,
        colors = colors,
        size = ButtonSizeType.EXTRA_LARGE,
        modifier = modifier,
        enabled = enabled,
        content = content,
    )
}

@Composable
fun NWKFillButton(
    onClick: () -> Unit,
    text: String,
    type: BoxButtonType,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isLoading: Boolean = false,
) {

    val colors = when (type) {
        BoxButtonType.PRIMARY -> NWKButtonDefaults.primaryButtonColors()
        BoxButtonType.BLACK -> NWKButtonDefaults.blackButtonColors()
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        Button(
            onClick = onClick,
            colors = colors,
            size = ButtonSizeType.EXTRA_LARGE,
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            content = {
                Text(
                    modifier = Modifier,
                    text = text.takeIf { isLoading.not() } ?: "",
                    style = NWKTheme.typography.heading6.copy(
                        color = NWKTheme.color.Neutral.white,
                    ),
                    maxLines = 1,
                )
            },
        )
        if (isLoading) {
            val lottieComposition by rememberLottieComposition(
                spec = LottieCompositionSpec.RawRes(NWKDrawableResource.ButtonLoading),
            )
            val foreverProgress by animateLottieCompositionAsState(
                composition = lottieComposition,
                iterations = LottieConstants.IterateForever,
                speed = 1f,
            )

            LottieAnimation(
                modifier = Modifier.width(120.dp).height(27.dp),
                composition = lottieComposition,
                progress = { foreverProgress },
            )
        }

    }

}

@Preview
@Composable
private fun NWKFillButtonPreview() {
    NWKTheme {
        NWKScaffold {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                NWKFillButton(
                    onClick = {},
                    text = "BTN",
                    modifier = Modifier.fillMaxWidth(),
                    type = BoxButtonType.PRIMARY,
                )
                NWKFillButton(
                    onClick = {},
                    text = "BTNBTNBTNBTNBTNBTNBTNBTNBTNBTNBTNBTN",
                    modifier = Modifier.fillMaxWidth(),
                    type = BoxButtonType.BLACK,
                )
                NWKFillButton(
                    onClick = {},
                    modifier = Modifier.fillMaxWidth(),
                    type = BoxButtonType.BLACK,
                    enabled = false,
                    content = {
                        Text("asdfasdf")
                    },
                )
                NWKFillButton(
                    onClick = {},
                    text = "BTNBTNBTNBTNBTNBTNBTNBTNBTNBTNBTNBTN",
                    modifier = Modifier.fillMaxWidth(),
                    type = BoxButtonType.BLACK,
                    isLoading = true
                )
                NWKFillButton(
                    onClick = {},
                    text = "isLoading = false",
                    modifier = Modifier.fillMaxWidth(),
                    type = BoxButtonType.BLACK,
                    isLoading = false
                )
            }
        }
    }
}
