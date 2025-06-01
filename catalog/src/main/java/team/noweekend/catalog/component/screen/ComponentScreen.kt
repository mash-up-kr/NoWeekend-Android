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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                    style = TextStyle(
                        fontSize = 24.sp,
                        color = Color.Black,
                    ),
                )
            }
        },
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            contentPadding = PaddingValues(vertical = 16.dp),
        ) {
            item {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Component 설명",
                        style = TextStyle(
                            fontSize = 18.sp,
                        ),
                    )
                    Spacer(modifier = Modifier.size(16.dp))
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = uiState.component.description,
                        style = TextStyle(
                            fontSize = 16.sp,
                        ),
                    )
                    Spacer(modifier = Modifier.size(32.dp))
                }
            }
            item {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "NDS Component Example",
                    style = TextStyle(
                        fontSize = 18.sp,
                    ),
                )
                Spacer(modifier = Modifier.size(16.dp))
            }
            if (uiState.component.examples.isNotEmpty()) {
                itemsIndexed(uiState.component.examples) { index, example ->
                    val isLastItem = index == uiState.component.examples.lastIndex
                    ExampleItem(
                        example = example,
                        onExampleClick = {},
                    )
                    if (isLastItem.not()) {
                        Spacer(modifier = Modifier.size(8.dp))
                    }
                }
            } else {
                item {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(30.dp),
                        text = "No Example",
                        style = TextStyle(
                            fontSize = 14.sp,
                        ),
                        textAlign = TextAlign.Center,
                    )
                    Spacer(modifier = Modifier.size(16.dp))
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
