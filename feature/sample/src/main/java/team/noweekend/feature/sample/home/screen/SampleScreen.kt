package team.noweekend.feature.sample.home.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.noweekend.feature.sample.home.mvi.SampleUiState

@Composable
fun SampleScreen(
    modifier: Modifier = Modifier,
    uiState: SampleUiState,
    onDetailButtonClick: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text("Welcome to NoWeekend Android Team")
        Button(
            modifier = Modifier.widthIn(100.dp),
            onClick = onDetailButtonClick,
        ) {
            Text("멤버 보러가기")
        }
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
