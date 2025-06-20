package team.noweekend.core.common.ui.calendar.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.gestures.animateTo
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import team.noweekend.core.common.ui.calendar.state.CalendarPagerState.CalendarMode
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import kotlin.math.roundToInt

@Composable
fun CalendarTypeToggle(
    modifier: Modifier = Modifier,
    currentCalendarMode: State<CalendarMode> = remember { mutableStateOf(CalendarMode.WEEK) },
    onToggleStateChanged: (Boolean) -> Unit = {},
    onClickToggle: () -> Unit = {},
) {
    val density = LocalDensity.current
    val height = 38.dp
    val thumbSize = 32.dp
    val width = 72.dp
    val thumbPadding = PaddingValues(horizontal = 4.dp, vertical = 3.dp)
    val maxDragDistance = with(density) { (width - thumbSize - 4.dp * 2).toPx() }

    val anchoredDraggableState = remember {
        AnchoredDraggableState(
            initialValue = if (currentCalendarMode.value == CalendarMode.WEEK) {
                CalendarMode.WEEK
            } else {
                CalendarMode.MONTH
            },
            anchors = DraggableAnchors {
                CalendarMode.WEEK at 0f
                CalendarMode.MONTH at maxDragDistance
            },
        )
    }

    LaunchedEffect(currentCalendarMode.value) {
        if (currentCalendarMode.value != anchoredDraggableState.currentValue) {
            anchoredDraggableState.animateTo(
                if (currentCalendarMode.value == CalendarMode.WEEK) CalendarMode.WEEK else CalendarMode.MONTH,
            )
        }
    }

    LaunchedEffect(anchoredDraggableState.currentValue) {
        onToggleStateChanged(anchoredDraggableState.currentValue == CalendarMode.MONTH)
    }

    Box(
        modifier = modifier
            .width(width)
            .height(height)
            .clip(CircleShape)
            .background(color = NWKTheme.color.Neutral.neutralGray200)
            .padding(thumbPadding)
            .anchoredDraggable(
                state = anchoredDraggableState,
                orientation = Orientation.Horizontal,
            )
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
            ) {
                onClickToggle()
            },
    ) {
        CalendarTypeToggleBackground(
            modifier = Modifier.fillMaxSize(),
        )
        CalendarTypeToggleThumb(
            modifier = Modifier.fillMaxSize(),
            currentCalendarMode = currentCalendarMode,
            anchoredDraggableState = anchoredDraggableState,
        )
    }
}

@Composable
private fun CalendarTypeToggleThumb(
    anchoredDraggableState: AnchoredDraggableState<CalendarMode>,
    modifier: Modifier = Modifier,
    thumbSize: Dp = 32.dp,
    currentCalendarMode: State<CalendarMode> = remember { mutableStateOf(CalendarMode.WEEK) },
) {
    Row(modifier = modifier) {
        Box(
            Modifier
                .offset {
                    IntOffset(
                        x = anchoredDraggableState
                            .requireOffset()
                            .roundToInt(),
                        y = 0,
                    )
                }
                .shadow(5.dp, shape = CircleShape)
                .size(thumbSize)
                .clip(CircleShape)
                .background(Color.White),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = stringResource(id = currentCalendarMode.value.id),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

@Composable
private fun CalendarTypeToggleBackground(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = stringResource(id = CalendarMode.WEEK.id),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = NWKTheme.color.Semantic.Text.disabled,
        )
        Text(
            modifier = Modifier.weight(1f),
            text = stringResource(id = CalendarMode.MONTH.id),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = NWKTheme.color.Semantic.Text.disabled,
        )
    }
}

@Preview
@Composable
private fun PreviewCalendarTypeToggle() {
    NWKTheme {
        Column {
            val currentCalendarMode = remember { mutableStateOf(CalendarMode.WEEK) }
            CalendarTypeToggle(
                currentCalendarMode = currentCalendarMode,
                onToggleStateChanged = { isMonth ->
                    if (isMonth) {
                        currentCalendarMode.value = CalendarMode.MONTH
                    } else {
                        currentCalendarMode.value = CalendarMode.WEEK
                    }
                },
                onClickToggle = {
                    currentCalendarMode.value =
                        if (currentCalendarMode.value == CalendarMode.WEEK) CalendarMode.MONTH else CalendarMode.WEEK
                },
            )
        }
    }
}
