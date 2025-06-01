package team.noweekend.catalog.model

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

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

internal val Components: ImmutableList<Component> = listOf(
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
