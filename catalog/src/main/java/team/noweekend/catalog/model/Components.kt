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
        val ButtonComponent: Component = Component(
            id = 0,
            name = "Button",
            imageUrl = "",
            description = "description",
            examples = Button.Examples,
        )

        val InputComponent: Component = Component(
            id = 1,
            name = "Input",
            imageUrl = "",
            description = "description",
            examples = Input.Examples,
        )

        val CalendarComponent: Component = Component(
            id = 2,
            name = "Calendar",
            imageUrl = "",
            description = "description",
            examples = Calendar.Examples,
        )

        val CardComponent: Component = Component(
            id = 3,
            name = "Card",
            imageUrl = "",
            description = "description",
            examples = Card.Examples,
        )

        val SwitchComponent: Component = Component(
            id = 4,
            name = "Switch",
            imageUrl = "",
            description = "description",
            examples = Switch.Examples,
        )

        val CheckBoxComponent: Component = Component(
            id = 5,
            name = "CheckBox",
            imageUrl = "",
            description = "description",
            examples = CheckBox.Examples,
        )

        val DividerComponent: Component = Component(
            id = 6,
            name = "Divider",
            imageUrl = "",
            description = "description",
            examples = Divider.Examples,
        )

        val HeaderComponent: Component = Component(
            id = 7,
            name = "Header",
            imageUrl = "",
            description = "description",
            examples = Header.Examples,
        )

        val TabBarComponent: Component = Component(
            id = 8,
            name = "TabBar",
            imageUrl = "",
            description = "description",
            examples = TabBar.Examples,
        )

        val BottomSheetComponent: Component = Component(
            id = 9,
            name = "BottomSheet",
            imageUrl = "",
            description = "description",
            examples = BottomSheet.Examples,
        )

        val DialogComponent: Component = Component(
            id = 10,
            name = "Dialog",
            imageUrl = "",
            description = "description",
            examples = Dialog.Examples,
        )
    }
}

internal val NDSComponents: ImmutableList<Component> =
    listOf(
        Component.ButtonComponent,
        Component.InputComponent,
        Component.CalendarComponent,
        Component.CardComponent,
        Component.SwitchComponent,
        Component.CheckBoxComponent,
        Component.DividerComponent,
        Component.HeaderComponent,
        Component.TabBarComponent,
        Component.BottomSheetComponent,
        Component.DialogComponent,
    ).toImmutableList()
