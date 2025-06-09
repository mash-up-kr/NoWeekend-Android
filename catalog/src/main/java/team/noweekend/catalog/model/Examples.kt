package team.noweekend.catalog.model

import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import kotlinx.serialization.Serializable
import team.noweekend.core.design.system.core.component.button.BoxButton
import team.noweekend.core.design.system.core.component.button.BoxButtonType

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
                name = "ActiveBoxButton",
                description = ButtonExampleDescription,
            ) {
                BoxButton(
                    onClick = {},
                    type = BoxButtonType.ACTIVE,
                ) {
                    Text("Active")
                }
            },
            Example(
                name = "DefaultBoxButton",
                description = ButtonExampleDescription,
            ) {
                BoxButton(
                    onClick = {},
                    type = BoxButtonType.DEFAULT,
                ) {
                    Text("Default")
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
