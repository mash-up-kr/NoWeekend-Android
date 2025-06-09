package team.noweekend.catalog.model

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.serialization.Serializable

@Serializable
data class Component(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val description: String,
    val examples: List<Example>,
) {
    companion object {
        val ButtonComponent = Component(
            id = 0,
            name = "Button",
            imageUrl = "",
            description = "description",
            examples = Button.Examples,
        )

        val TextFieldComponent = Component(
            id = 1,
            name = "TextField",
            imageUrl = "",
            description = "description",
            examples = TextField.Examples,
        )
    }
}

internal val NDSComponents: ImmutableList<Component> =
    listOf(
        Component.ButtonComponent,
        Component.TextFieldComponent,
    ).toImmutableList()
