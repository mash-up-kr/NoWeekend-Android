package team.noweekend.catalog.model

import androidx.compose.runtime.Composable

internal data class Example(
    val name: String,
    val description: String,
    val content: @Composable () -> Unit,
)

internal object Button {
    private const val ButtonExampleDescription = "Button examples"
    val Examples =
        listOf(
            Example(
                name = "BoxButton",
                description = ButtonExampleDescription,
            ) {
                /**
                 * Design system component
                 * e.g. NWKBoxButton()
                 **/
            },
            Example(
                name = "CapsuleButton",
                description = ButtonExampleDescription,
            ) {
                /**
                 * Design system component
                 * e.g. NWKCapsuleButton()
                 **/
            },
        )
}


