package team.noweekend.core.design.system.core.component.button.defaults

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

enum class ButtonSizeType(
    val horizontalPadding: Dp,
    val verticalPadding: Dp,
) {
    MEDIUM(
        horizontalPadding = 16.dp,
        verticalPadding = 8.dp,
    ),
    EXTRA_LARGE(
        horizontalPadding = 16.dp,
        verticalPadding = 18.dp,
    ),
    ;
}
