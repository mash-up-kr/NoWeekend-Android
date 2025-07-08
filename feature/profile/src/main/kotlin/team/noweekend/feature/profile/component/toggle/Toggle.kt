package team.noweekend.feature.profile.component.toggle


import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.gestures.animateTo
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import kotlin.math.roundToInt

@Composable
fun Toggle(
    modifier: Modifier = Modifier,
    width : Dp = 39.dp,
    height : Dp = 24.dp,
    thumbSize: Dp = 18.dp,
    thumbPadding: Dp = 3.dp,
    toggleState: ToggleState = ToggleState.OFF,
    onToggleStateChanged: (Boolean) -> Unit = {},
    onClickToggle: () -> Unit = {},
) {
    val density = LocalDensity.current
    val thumbPaddingValues = PaddingValues(thumbPadding)
    val maxDragDistance = with(density) { (width - thumbSize - thumbPadding * 2).toPx() }

    val anchoredDraggableState = remember {
        AnchoredDraggableState(
            initialValue = if (toggleState == ToggleState.OFF) {
                ToggleState.OFF
            } else {
                ToggleState.ON
            },
            anchors = DraggableAnchors {
                ToggleState.OFF at 0f
                ToggleState.ON at maxDragDistance
            },
        )
    }

    val animatedBackgroundColor by animateColorAsState(
        if (toggleState == ToggleState.ON) {
            NWKTheme.color.Neutral.neutralGray900
        } else {
            NWKTheme.color.Neutral.neutralGray200
        },
    )

    LaunchedEffect(toggleState) {
        if (toggleState != anchoredDraggableState.currentValue) {
            anchoredDraggableState.animateTo(
                if (toggleState == ToggleState.OFF) ToggleState.OFF else ToggleState.ON,
            )
        }
    }

    LaunchedEffect(anchoredDraggableState.currentValue) {
        onToggleStateChanged(anchoredDraggableState.currentValue == ToggleState.ON)
    }

    Box(
        modifier = modifier
            .width(width)
            .height(height)
            .clip(CircleShape)
            .background(color = animatedBackgroundColor)
            .padding(thumbPaddingValues)
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

        ToggleThumb(
            modifier = Modifier.fillMaxSize(),
            thumbSize = thumbSize,
            anchoredDraggableState = anchoredDraggableState,
        )
    }
}

@Composable
private fun ToggleThumb(
    anchoredDraggableState: AnchoredDraggableState<ToggleState>,
    modifier: Modifier = Modifier,
    thumbSize: Dp = 18.dp,
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
                .shadow(elevation = 5.dp, shape = CircleShape)
                .size(size = thumbSize)
                .clip(shape = CircleShape)
                .background(color = NWKTheme.color.Neutral.white),
            contentAlignment = Alignment.Center,
        ) {}
    }
}

@Preview
@Composable
private fun PreviewCalendarTypeToggle() {
    NWKTheme {
        Column(modifier = Modifier.background(color = NWKTheme.color.Neutral.white)) {
            var toggleState by remember { mutableStateOf(ToggleState.OFF) }
            Toggle(
                toggleState = toggleState,
                onToggleStateChanged = { isOn ->
                    if (isOn) {
                        toggleState = ToggleState.ON
                    } else {
                        toggleState = ToggleState.OFF
                    }
                },
                onClickToggle = {
                    toggleState = if (toggleState == ToggleState.OFF) {
                        ToggleState.ON
                    } else {
                        ToggleState.OFF
                    }
                },
            )
        }
    }
}
