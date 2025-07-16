package team.noweekend.catalog.home.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.noweekend.catalog.home.mvi.HomeUiState
import team.noweekend.catalog.model.Component
import team.noweekend.core.design.system.core.component.scaffold.NWKScaffold
import team.noweekend.core.design.system.foundation.theme.NWKTheme

@Composable
internal fun HomeScreen(
    uiState: HomeUiState,
    onComponentClick: (Component) -> Unit,
    modifier: Modifier = Modifier,
) {
    NWKScaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 20.dp,
                        vertical = 16.dp,
                    ),
                text = "NDS Catalog",
                style = NWKTheme.typography.heading3,
            )
        },
    ) {
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            content = {
                items(uiState.components) { component ->
                    ComponentItem(
                        component = component,
                        onItemClick = onComponentClick,
                    )
                }
            },
            contentPadding = PaddingValues(vertical = 16.dp),
        )
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    NWKTheme {
        HomeScreen(
            uiState = HomeUiState.INITIAL_STATE,
            onComponentClick = {},
        )
    }
}
