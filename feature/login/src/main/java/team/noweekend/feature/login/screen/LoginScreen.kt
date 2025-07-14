package team.noweekend.feature.login.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.noweekend.core.design.system.core.component.image.NWKImage
import team.noweekend.core.design.system.core.component.scaffold.NWKScaffold
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.core.resource.NWKDrawableResource
import team.noweekend.core.resource.NWKStringResource
import team.noweekend.feature.login.mvi.LoginUiState

@Composable
fun LoginScreen(
    uiState: LoginUiState,
    onClickGoogleLogin: () -> Unit,
    modifier: Modifier = Modifier,
) {
    NWKScaffold(modifier = modifier) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(NWKTheme.color.Semantic.Background.normal),
        ) {
            TitleContainer(modifier = Modifier.align(Alignment.Center))
            GoogleLoginButton(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, bottom = 40.dp),
                onClickGoogleLogin = onClickGoogleLogin,
            )
        }
    }
}

@Composable
private fun GoogleLoginButton(modifier: Modifier = Modifier, onClickGoogleLogin: () -> Unit) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .border(width = 1.dp, color = NWKTheme.color.Semantic.Border.border02, shape = RoundedCornerShape(12.dp))
            .clickable { onClickGoogleLogin() }
            .padding(vertical = 16.dp, horizontal = 24.dp),
        horizontalArrangement = Arrangement.Center,
    ) {
        NWKImage(
            modifier = Modifier.size(24.dp),
            drawableResId = NWKDrawableResource.GoogleLogo,
        )
        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = stringResource(NWKStringResource.GoogleLoginLabel),
            style = NWKTheme.typography.body1,
            color = NWKTheme.color.Semantic.Text.neutral,
        )
    }
}

@Composable
private fun TitleContainer(modifier: Modifier = Modifier) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = stringResource(NWKStringResource.LoginTitle),
            style = NWKTheme.typography.heading6,
            color = NWKTheme.color.Toast.toast500,
        )
        NWKImage(
            drawableResId = NWKDrawableResource.AppTitle,
        )
        NWKImage(
            modifier = Modifier.padding(top = 5.dp),
            drawableResId = NWKDrawableResource.AppLogo,
        )
    }
}

@Preview
@Composable
private fun LoginScreenPreview() {
    NWKTheme {
        LoginScreen(
            modifier = Modifier.fillMaxSize(),
            uiState = LoginUiState.INITIAL_STATE,
            onClickGoogleLogin = {},
        )
    }
}
