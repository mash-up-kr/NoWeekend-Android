package team.noweekend.feature.calendar.component.todoList

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import team.noweekend.core.common.android.extension.fillMaxWidthOfScreen
import team.noweekend.core.design.system.foundation.theme.NWKTheme

@Composable
fun CalendarTodoBoardDivider(
    state: LazyListState,
    modifier: Modifier= Modifier
){

    val isCanScrollBackward = remember { derivedStateOf { state.canScrollBackward } }

    // 색상 애니메이션 정의
    val startColor = NWKTheme.color.Neutral.neutralGray100
    val endColor by animateColorAsState(
        targetValue = if (isCanScrollBackward.value.not()) startColor else NWKTheme.color.Neutral.white,
        animationSpec = tween(durationMillis = 100),
    )
    Canvas(
        modifier = modifier
            .fillMaxWidthOfScreen()
            .height(8.dp),
    ) {
        drawLine(
            brush = Brush.verticalGradient(
                colors = listOf(startColor, endColor),
            ),
            start = Offset(0f, size.height / 2),
            end = Offset(size.width, size.height / 2),
            strokeWidth = 8.dp.toPx(),
        )
    }
}
