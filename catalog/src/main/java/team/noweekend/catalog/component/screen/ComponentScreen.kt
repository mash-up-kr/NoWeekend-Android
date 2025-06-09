package team.noweekend.catalog.component.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.noweekend.catalog.component.mvi.ComponentUiState
import team.noweekend.core.design.system.core.component.scaffold.NWKScaffold
import team.noweekend.core.design.system.foundation.theme.NWKTheme

@Composable
internal fun ComponentScreen(
    onBackClick: () -> Unit,
    uiState: ComponentUiState,
    modifier: Modifier = Modifier,
) {
    NWKScaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 20.dp,
                        vertical = 16.dp,
                    ),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    modifier = Modifier
                        .size(28.dp)
                        .clickable(onClick = onBackClick),
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "",
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = uiState.component.name,
                    style = NWKTheme.typography.heading3,
                )
            }
        },
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            contentPadding = PaddingValues(vertical = NWKTheme.spacing.space175),
        ) {
            item {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Component 설명",
                        style = NWKTheme.typography.body1,
                    )
                    Spacer(modifier = Modifier.size(NWKTheme.spacing.space175))
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = uiState.component.description,
                        style = NWKTheme.typography.body2,
                    )
                    Spacer(modifier = Modifier.size(NWKTheme.spacing.space400))
                }
            }
            item {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "NDS Component Example",
                    style = NWKTheme.typography.body1,
                )
                Spacer(modifier = Modifier.size(NWKTheme.spacing.space175))
            }
            if (uiState.component.examples.isNotEmpty()) {
                itemsIndexed(uiState.component.examples) { index, example ->
                    val isLastItem = index == uiState.component.examples.lastIndex
                    ExampleItem(
                        example = example,
                        onExampleClick = {},
                    )
                    if (isLastItem.not()) {
                        Spacer(modifier = Modifier.size(NWKTheme.spacing.space100))
                    }
                }
            } else {
                item {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(NWKTheme.spacing.space175),
                        text = "No Example",
                        style = NWKTheme.typography.body2,
                        textAlign = TextAlign.Center,
                    )
                    Spacer(modifier = Modifier.size(NWKTheme.spacing.space175))
                }
            }
        }
    }
}

@Preview
@Composable
private fun ComponentScreenPreview() {
    NWKTheme {
        ComponentScreen(
            onBackClick = {},
            uiState = ComponentUiState.INITIAL_STATE,
        )
    }
}
