package team.noweekend.feature.profile.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest
import team.noweekend.feature.profile.mvi.ProfileIntent
import team.noweekend.feature.profile.mvi.ProfileUiState
import team.noweekend.feature.profile.mvi.ProfileViewModel
import team.noweekend.feature.profile.mvi.rememberProfileSideEffectHandler

@Composable
internal fun ProfileRoute(
    navigateToExternalWebBrowser: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = hiltViewModel(),
) {
    val uiState: State<ProfileUiState> = viewModel.uiState.collectAsStateWithLifecycle()

    val profileSideEffectHandler = rememberProfileSideEffectHandler(
        navigateToExternalWebBrowser = navigateToExternalWebBrowser,
    )

    LaunchedEffect(key1 = Unit) {
        viewModel.sideEffect.collectLatest { sideEffect ->
            profileSideEffectHandler.handleSideEffect(sideEffect)
        }
    }

    ProfileScreen(
        uiState = uiState,
        onMenuClick = { viewModel.intent(ProfileIntent.ClickMenu(it)) },
        modifier = modifier,
    )
}
