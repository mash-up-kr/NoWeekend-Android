package team.noweekend.feature.login.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import team.noweekend.feature.login.mvi.LoginUiState

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    uiState: LoginUiState,
    onClickGoogleLogin: () -> Unit,
) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            modifier = Modifier.clickable { onClickGoogleLogin() },
            text = "구글 로그인",
        )
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen(
        modifier = Modifier.fillMaxSize(),
        uiState = LoginUiState.INITIAL_STATE,
        onClickGoogleLogin = {},
    )
}
