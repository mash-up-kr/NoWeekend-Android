package team.noweekend.core.common.ui.datepicker.core

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.flow.collectLatest
import team.noweekend.core.design.system.foundation.theme.NWKTheme

@Composable
internal fun <T> WheelPicker(
    visibleItemCount: Int,
    initialIndex: Int,
    itemList: ImmutableList<T>,
    modifier: Modifier = Modifier,
    itemHeight: Dp = 35.dp,
    onItemSelected: (Int) -> Unit = {},
) {
    val containerHeight: Dp = visibleItemCount * itemHeight

    val state = rememberLazyListState(initialFirstVisibleItemIndex = initialIndex)

    LaunchedEffect(Unit) {
        snapshotFlow { state.layoutInfo.visibleItemsInfo }.collectLatest { list ->
            val selectedIndex = list.firstOrNull { it.offset == 0 }
            if (selectedIndex != null) {
                onItemSelected(selectedIndex.index)
            }
        }
    }

    LazyColumn(
        state = state,
        modifier = modifier.fillMaxWidth()
            .height(containerHeight),
        contentPadding = PaddingValues(vertical = containerHeight / 2 - itemHeight / 2),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        flingBehavior = rememberSnapFlingBehavior(lazyListState = state),
    ) {
        itemsIndexed(itemList) { index, it ->

            val isCenter = remember {
                derivedStateOf {
                    index == state.layoutInfo.visibleItemsInfo.find { it.offset == 0 }?.index
                }
            }

            val color = animateColorAsState(
                if (isCenter.value) {
                    NWKTheme.color.Semantic.Text.neutral
                } else {
                    NWKTheme.color.Neutral.neutralGray300
                },
            )

            Row(
                modifier = Modifier.height(itemHeight),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = it.toString(),
                    style = NWKTheme.typography.heading4.copy(
                        fontWeight = FontWeight.Normal,
                    ),
                    color = color.value,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}
