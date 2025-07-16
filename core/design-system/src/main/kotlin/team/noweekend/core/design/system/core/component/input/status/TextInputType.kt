package team.noweekend.core.design.system.core.component.input.status

import androidx.compose.ui.text.input.KeyboardType

enum class TextInputType(
    val keyboardType: KeyboardType,
) {
    NICKNAME(
        keyboardType = KeyboardType.Text,
    ),
    TEXT(
        keyboardType = KeyboardType.Text,
    ),
    DAY(
        keyboardType = KeyboardType.Number,
    ),
    TIME(
        keyboardType = KeyboardType.Number,
    ),
    NUMBER(
        keyboardType = KeyboardType.Number,
    ),
    ;
}
