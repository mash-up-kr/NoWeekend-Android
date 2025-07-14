package team.noweekend.feature.splash.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.noweekend.core.design.system.core.component.image.NWKImage
import team.noweekend.core.design.system.core.component.scaffold.NWKScaffold
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.core.resource.NWKDrawableResource
import team.noweekend.core.resource.NWKStringResource

@Composable
fun SplashScreen(modifier: Modifier = Modifier) {
    NWKScaffold(modifier = modifier) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(NWKTheme.color.Semantic.Background.normal),
        ) {
            LogoContainer(modifier = Modifier.align(Alignment.Center))
        }
    }
}

@Composable
private fun LogoContainer(modifier: Modifier = Modifier) {
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

@Composable
@Preview
private fun SplashScreenPreview() {
    SplashScreen(modifier = Modifier.fillMaxSize())
}
