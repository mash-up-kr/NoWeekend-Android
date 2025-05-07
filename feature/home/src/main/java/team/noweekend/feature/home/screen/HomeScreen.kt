package team.noweekend.feature.home.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import team.noweekend.feature.home.theme.NoWeekendTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onNavigateButtonClick: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to the Home Screen")
        Button(
            onClick = onNavigateButtonClick
        ) {
            Text("Go To Sample")
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    NoWeekendTheme {
        Box(
            modifier = Modifier.background(Color.White)
        ) {
            HomeScreen(
                modifier = Modifier.fillMaxSize(),
                onNavigateButtonClick = {}
            )
        }
    }
}
