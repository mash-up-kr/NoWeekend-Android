package team.noweekend.core.common.ui.datepicker.core

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import kotlin.math.abs

@Composable
internal fun <T> WheelPicker(
    visibleItemCount: Int,
    initialIndex: Int,
    itemList: ImmutableList<T>,
    paddingDirection: PaddingDirection,
    modifier: Modifier = Modifier,
    itemHeight: Dp = 35.dp,
    onItemSelected: (Int) -> Unit = {},
) {
    val containerHeight: Dp = visibleItemCount * itemHeight

    val halfVisibleCount = (visibleItemCount - 1) / 2

    val emptyItems = List(halfVisibleCount) { null as T? }

    val extendedList: List<T?> = emptyItems + itemList + emptyItems

    val state = rememberLazyListState(initialFirstVisibleItemIndex = initialIndex)

    LaunchedEffect(Unit) {
        snapshotFlow { state.layoutInfo.visibleItemsInfo }.collectLatest { list ->
            val viewportCenter = state.layoutInfo.viewportSize.height / 2
            val selectedInfo = list.minByOrNull {
                abs(it.offset + it.size / 2 - viewportCenter)
            }
            val selectedIndex = selectedInfo?.index?.minus(halfVisibleCount)
            if (selectedIndex != null && selectedIndex >= 0 && selectedIndex < itemList.size) {
                onItemSelected(selectedIndex)
            }
        }
    }

    LazyColumn(
        state = state,
        modifier = modifier
            .fillMaxWidth()
            .height(containerHeight),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        flingBehavior = rememberSnapFlingBehavior(lazyListState = state),
    ) {
        itemsIndexed(extendedList) { index, it ->

            val isCenter = remember {
                derivedStateOf {
                    val viewportCenter = state.layoutInfo.viewportSize.height / 2
                    val centerInfo = state.layoutInfo.visibleItemsInfo.minByOrNull {
                        abs(it.offset + it.size / 2 - viewportCenter)
                    }
                    index == centerInfo?.index
                }
            }

            val color = animateColorAsState(
                if (isCenter.value) {
                    NWKTheme.color.Semantic.Text.neutral
                } else {
                    NWKTheme.color.Neutral.neutralGray300
                },
            )

            val paddingModifier = when (paddingDirection) {
                PaddingDirection.Left -> {
                    Modifier.padding(start = 64.dp)
                }
                PaddingDirection.Right -> {
                    Modifier.padding(end = 64.dp)
                }
                else -> {
                    Modifier
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth().then(paddingModifier).height(itemHeight),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                if (it != null) {
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
}

enum class PaddingDirection {
    Left, Right, Center
}
