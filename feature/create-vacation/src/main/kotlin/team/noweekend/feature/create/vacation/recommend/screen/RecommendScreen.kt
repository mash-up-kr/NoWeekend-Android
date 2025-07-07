package team.noweekend.feature.create.vacation.recommend.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.noweekend.core.common.android.extension.fillMaxWidthOfScreen
import team.noweekend.core.design.system.core.component.icon.NWKIcon
import team.noweekend.core.design.system.core.component.scaffold.NWKScaffold
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.core.resource.NWKDrawableResource
import team.noweekend.feature.create.vacation.recommend.component.loading.LoadingComponent
import team.noweekend.feature.create.vacation.recommend.component.result.ToastResultComponent
import team.noweekend.feature.create.vacation.recommend.mvi.RecommendUiState

@Composable
internal fun RecommendScreen(
    uiState: State<RecommendUiState>,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val isLoading: State<Boolean> = rememberUpdatedState(
        newValue = uiState.value.isLoading
    )
    NWKScaffold(
        modifier = modifier,
        containerColor = NWKTheme.color.Toast.toast50,
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 16.dp),
            ) {
                NWKIcon(
                    resourceId = NWKDrawableResource.ChevronLeft,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable(onClick = onBackClick),
                    tint = NWKTheme.color.Semantic.Text.body,
                )
            }
        },
        content = { paddingValues ->
            RecommendScreenContent(
                isLoading = isLoading,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
            )
        },
    )
}

@Composable
private fun RecommendScreenContent(
    isLoading: State<Boolean>,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
    ) {
        ToastResultComponent(
            isLoading = isLoading,
            modifier = Modifier.align(Alignment.BottomCenter),
        )
        Image(
            modifier = Modifier
                .fillMaxWidthOfScreen()
                .align(Alignment.BottomCenter),
            painter = painterResource(NWKDrawableResource.Toaster),
            contentDescription = null,
        )
        if (isLoading.value) {
            LoadingComponent(
                modifier = Modifier,
            )
        }
    }
}

@Preview
@Composable
private fun RecommendScreenPreview() {
    val uiState = remember { mutableStateOf(RecommendUiState.INITIAL_STATE) }
    NWKTheme {
        RecommendScreen(
            uiState = uiState,
            onBackClick = {},
        )
    }
}
