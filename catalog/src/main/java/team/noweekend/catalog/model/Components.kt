package team.noweekend.catalog.model

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.serialization.Serializable

@Serializable
internal data class Component(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val description: String,
    val examples: List<Example>,
) {
    companion object {
        val ButtonComponent = Component(
            id = 1,
            name = "Button",
            imageUrl = "",
            description = "description",
            examples = Button.Examples,
        )
    }
}

internal val NDSComponents: ImmutableList<Component> = listOf(
    Component.ButtonComponent,
    Component.ButtonComponent,
    Component.ButtonComponent,
    Component.ButtonComponent,
    Component.ButtonComponent,
    Component.ButtonComponent,
    Component.ButtonComponent,
    Component.ButtonComponent,
    Component.ButtonComponent,
    Component.ButtonComponent,
    Component.ButtonComponent,
    Component.ButtonComponent,
    Component.ButtonComponent,
    Component.ButtonComponent,
    Component.ButtonComponent,
).toImmutableList()
