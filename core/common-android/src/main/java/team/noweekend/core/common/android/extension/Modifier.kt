package team.noweekend.core.common.android.extension

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.dp
import team.noweekend.core.common.android.util.getScreenWidth

@Composable
fun Modifier.fillMaxWidthOfScreen(screenWidth: Int = getScreenWidth()): Modifier =
    this.then(
        Modifier
            .layout { measurable, constraints ->
                val placeable = measurable.measure(
                    constraints.copy(maxWidth = screenWidth.dp.value.toInt()),
                )
                layout(placeable.width.toDp().roundToPx(), placeable.height) {
                    placeable.place(0, 0)
                }
            }
            .fillMaxWidth(),
    )
