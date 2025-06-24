package team.noweekend.feature.home.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import team.noweekend.core.design.system.core.component.scaffold.NWKScaffold
import team.noweekend.core.design.system.foundation.theme.NWKTheme

@Composable
internal fun HomeScreen(
    modifier: Modifier = Modifier,
) {
    NWKScaffold(
        modifier = modifier,
        topBar = {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = NWKTheme.spacing.space175,
                        start = NWKTheme.spacing.space300,
                        end = NWKTheme.spacing.space300,
                        bottom = NWKTheme.spacing.space100,
                    ),
                text = "오늘 연차쓸래?",
                style = NWKTheme.typography.heading4.copy(
                    color = NWKTheme.color.Semantic.Text.neutral,
                ),
            )
        },
        content = {
            HomeScreenContent(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
            )
        },
    )
}

@Composable
private fun HomeScreenContent(
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
    ) {
        item {
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    NWKTheme {
        HomeScreen()
    }
}
