package team.noweekend.feature.onboarding.profile.screen

import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import team.noweekend.core.design.system.foundation.theme.NWKTheme

@Composable
fun ProfileInputScreen(
    onBackClick: () -> Unit,
    onConfirmClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val textFieldState = rememberTextFieldState()
    val keyboardController = LocalSoftwareKeyboardController.current

//    NWKScaffold(
//        modifier = modifier,
//        topBar = {
//            NWKHeader(
//                text = "1/3",
//                onBackClick = onBackClick,
//            )
//        },
//        content = {
//            ProfileInputScreenContent(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(it),
//            )
//        },
//        bottomBar = {
//            NWKFillButton(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 20.dp, vertical = 8.dp),
//                onClick = onConfirmClick,
//                text = stringResource(NWKStringResource.Next),
//                type = BoxButtonType.BLACK,
//                enabled = true,
//            )
//        },
//    )
}


@Preview
@Composable
private fun ProfileInputScreenPreview() {
    NWKTheme {
        ProfileInputScreen(
            onBackClick = {},
            onConfirmClick = {},
        )
    }
}
