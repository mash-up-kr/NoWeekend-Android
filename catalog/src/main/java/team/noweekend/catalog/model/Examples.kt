package team.noweekend.catalog.model

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.serialization.Serializable
import team.noweekend.core.design.system.core.component.button.defaults.BoxButtonType
import team.noweekend.core.design.system.core.component.button.fill.NWKFillButton
import team.noweekend.core.design.system.core.component.button.outline.NWKOutlineButton
import team.noweekend.core.design.system.core.component.divider.NWKHorizontalDivider

@Serializable
data class Example(
    val name: String,
    val description: String,
    val content: @Composable () -> Unit,
)

internal object Button {
    private const val ButtonExampleDescription = "Button examples"
    val Examples: List<Example> =
        listOf(
            Example(
                name = "FillButton",
                description = ButtonExampleDescription,
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    NWKFillButton(
                        onClick = {},
                        text = "BTN",
                        modifier = Modifier.fillMaxWidth(),
                        type = BoxButtonType.PRIMARY,
                    )
                    NWKFillButton(
                        onClick = {},
                        text = "BTN",
                        modifier = Modifier.fillMaxWidth(),
                        type = BoxButtonType.BLACK,
                    )
                }
            },
            Example(
                name = "OutlineButton",
                description = ButtonExampleDescription,
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    NWKOutlineButton(
                        onClick = {},
                        text = "BTN",
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
            },
        )
}

internal object Input {
    private const val TextFieldExampleDescription = "Input examples"
    val Examples: List<Example> =
        listOf(
            Example(
                name = "Input",
                description = TextFieldExampleDescription,
            ) {
                TextField(
                    value = "",
                    onValueChange = {},
                )
            },
        )
}

internal object Calendar {
    private const val CalendarExampleDescription = "Calendar examples"
    val Examples: List<Example> =
        listOf(
            Example(
                name = "Calendar",
                description = CalendarExampleDescription,
            ) {
                TextField(
                    value = "",
                    onValueChange = {},
                )
            },
        )
}

internal object Card {
    private const val CardExampleDescription = "Card examples"
    val Examples: List<Example> =
        listOf(
            Example(
                name = "Card",
                description = CardExampleDescription,
            ) {
                TextField(
                    value = "",
                    onValueChange = {},
                )
            },
        )
}

internal object Switch {
    private const val SwitchExampleDescription = "Switch examples"
    val Examples: List<Example> =
        listOf(
            Example(
                name = "Switch",
                description = SwitchExampleDescription,
            ) {
                TextField(
                    value = "",
                    onValueChange = {},
                )
            },
        )
}

internal object CheckBox {
    private const val CheckBoxExampleDescription = "CheckBox examples"
    val Examples: List<Example> =
        listOf(
            Example(
                name = "CheckBox",
                description = CheckBoxExampleDescription,
            ) {
                TextField(
                    value = "",
                    onValueChange = {},
                )
            },
        )
}

internal object Divider {
    private const val DividerExampleDescription = "Divider examples"
    val Examples: List<Example> =
        listOf(
            Example(
                name = "Divider",
                description = DividerExampleDescription,
            ) {
                val (shouldFillScreenWidth, setFillScreenWidth) = remember { mutableStateOf(false) }

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(100.dp, Alignment.CenterVertically),
                ) {
                    NWKHorizontalDivider(
                        shouldFillScreenWidth = shouldFillScreenWidth,
                        modifier = Modifier.fillMaxWidth(),
                    )
                    NWKOutlineButton(
                        onClick = { setFillScreenWidth(!shouldFillScreenWidth) },
                        text = "변경",
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
            },
        )
}

internal object Header {
    private const val HeaderExampleDescription = "Header examples"
    val Examples: List<Example> =
        listOf(
            Example(
                name = "Header",
                description = HeaderExampleDescription,
            ) {
                TextField(
                    value = "",
                    onValueChange = {},
                )
            },
        )
}

internal object TabBar {
    private const val TabBarExampleDescription = "TabBar examples"
    val Examples: List<Example> =
        listOf(
            Example(
                name = "TabBar",
                description = TabBarExampleDescription,
            ) {
                TextField(
                    value = "",
                    onValueChange = {},
                )
            },
        )
}

internal object BottomSheet {
    private const val BottomSheetExampleDescription = "BottomSheet examples"
    val Examples: List<Example> =
        listOf(
            Example(
                name = "BottomSheet",
                description = BottomSheetExampleDescription,
            ) {
                TextField(
                    value = "",
                    onValueChange = {},
                )
            },
        )
}

internal object Dialog {
    private const val DialogExampleDescription = "Dialog examples"
    val Examples: List<Example> =
        listOf(
            Example(
                name = "Dialog",
                description = DialogExampleDescription,
            ) {
                TextField(
                    value = "",
                    onValueChange = {},
                )
            },
        )
}
