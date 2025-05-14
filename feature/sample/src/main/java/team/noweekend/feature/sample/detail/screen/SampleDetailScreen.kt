package team.noweekend.feature.sample.detail.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import team.noweekend.feature.sample.detail.mvi.SampleDetailUiState
import team.noweekend.feature.sample.home.mvi.SampleUiState
import team.noweekend.feature.sample.home.screen.SampleScreen

@Composable
fun SampleDetailScreen(
    modifier: Modifier = Modifier,
    uiState: SampleDetailUiState,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(uiState.members.toString())
    }
}

@Preview
@Composable
private fun SampleScreenPreview() {
    SampleScreen(
        modifier = Modifier.fillMaxSize(),
        uiState = SampleUiState.INITIAL_STATE,
        onDetailButtonClick = {},
    )
}
