package team.noweekend.feature.home.screen

import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest
import team.noweekend.feature.home.mvi.HomeIntent
import team.noweekend.feature.home.mvi.HomeSideEffectHandler
import team.noweekend.feature.home.mvi.HomeUiState
import team.noweekend.feature.home.mvi.HomeViewModel
import team.noweekend.feature.home.mvi.rememberHomeSideEffectHandler

@Composable
internal fun HomeRoute(
    navigateToCreateVacation: (ActivityResultLauncher<Intent>) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val uiState: HomeUiState by viewModel.uiState.collectAsStateWithLifecycle()

    val createVacationLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.StartActivityForResult(),
            onResult = { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    Log.d("logtag", "Result OK")
                }
            }
        )

    val sideEffectHandler: HomeSideEffectHandler = rememberHomeSideEffectHandler(
        navigateToCreateVacation = { navigateToCreateVacation(createVacationLauncher) },
    )

    LaunchedEffect(key1 = Unit) {
        viewModel.sideEffect.collectLatest { sideEffectHandler.handleSideEffect(it) }
    }

    HomeScreen(
        uiState = uiState,
        onCreateVacationClick = { viewModel.intent(HomeIntent.ClickCreateVacation) },
    )
}
