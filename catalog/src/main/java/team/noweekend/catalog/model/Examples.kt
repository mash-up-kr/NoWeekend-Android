package team.noweekend.catalog.model

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.serialization.Serializable
import team.noweekend.core.design.system.core.component.button.defaults.BoxButtonType
import team.noweekend.core.design.system.core.component.button.fill.NWKFillButton
import team.noweekend.core.design.system.core.component.button.outline.NWKOutlineButton

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
                    verticalArrangement = Arrangement.spacedBy(8.dp)
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

internal object TextField {
    private const val TextFieldExampleDescription = "TextField examples"
    val Examples: List<Example> =
        listOf(
            Example(
                name = "TextField",
                description = TextFieldExampleDescription,
            ) {
                TextField(
                    value = "",
                    onValueChange = {},
                )
            },
        )
}
