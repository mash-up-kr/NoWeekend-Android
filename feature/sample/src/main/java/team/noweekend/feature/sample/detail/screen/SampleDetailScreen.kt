package team.noweekend.feature.sample.detail.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import team.noweekend.feature.sample.detail.mvi.SampleDetailUiState

@Composable
fun SampleDetailScreen(
    modifier: Modifier = Modifier,
    uiState: SampleDetailUiState,
    onBackClick: () -> Unit,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.TopStart)
                .clickable(onClick = onBackClick),
            text = "여기누르면 뒤로가요~",
        )
        Text(uiState.members.toString())
    }
}

@Preview
@Composable
private fun SampleDetailScreenPreview() {
    SampleDetailScreen(
        modifier = Modifier.fillMaxSize(),
        uiState = SampleDetailUiState.INITIAL_STATE,
        onBackClick = {},
    )
}
