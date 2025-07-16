package team.noweekend.feature.detail.date.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import team.noweekend.core.common.android.extension.fillMaxWidthOfScreen
import team.noweekend.core.common.ui.fab.FabLayout
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.feature.detail.date.mvi.DetailDateSideEffect
import team.noweekend.feature.detail.date.mvi.DetailDateViewModel
import team.noweekend.feature.detail.date.mvi.builder.rememberIntentBuilder

@Composable
internal fun DetailDateRoute(
    navigateToBack: () -> Unit,
    modifier: Modifier = Modifier,
    detailDateViewModel: DetailDateViewModel = hiltViewModel(),
) {
    val state = detailDateViewModel.uiState.collectAsStateWithLifecycle()

    val intentBuilder = rememberIntentBuilder(
        send = detailDateViewModel::intent,
    )

    LaunchedEffect(Unit) {
        with(intentBuilder) {
            getInitState()
            getRecommendTodoTagList()
        }
        detailDateViewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is DetailDateSideEffect.NavigateToAddTodo -> {}
                is DetailDateSideEffect.NavigateToBack -> {
                    navigateToBack()
                }
            }
        }
    }
    Box(
        modifier = modifier
            .fillMaxWidthOfScreen()
            .fillMaxHeight(),
    ) {
        var isExpanded by remember { mutableStateOf(false) }
        FabLayout(
            isExpanded = isExpanded,
            todoItemList = state.value.recommendTodoTagList,
            onClickFabButton = { isExpanded = isExpanded.not() },
            onClickTodo = intentBuilder::clickRecommendTodoTag,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 20.dp, end = 20.dp),
            onClickDirectInput = {},
            onClickDim = { isExpanded = isExpanded.not() },
        )

        DetailDateScreen(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = NWKTheme.spacing.space200),
            detailDateUiState = state.value,
            onClickCheckBox = intentBuilder::changeCompleteSchedule,
            onClickOptionButton = {},
            onClickBackButton = intentBuilder::clickBackButton,
        )
    }
}
