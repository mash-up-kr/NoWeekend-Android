package team.noweekend.feature.calendar.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import team.noweekend.core.resource.NWKDrawableResource.ArrowRight
import team.noweekend.core.resource.NWKDrawableResource.Delete
import team.noweekend.core.resource.NWKDrawableResource.Edit
import team.noweekend.core.resource.NWKStringResource.TodoAddSameActionTitle
import team.noweekend.core.resource.NWKStringResource.TodoDeleteActionTitle
import team.noweekend.core.resource.NWKStringResource.TodoEditActionTitle

sealed interface TodoRecordAction {

    val imageResId: Int
        @DrawableRes get
    val title: Int
        @StringRes get

    data class EditAction(
        override val title: Int = TodoEditActionTitle,
        override val imageResId: Int = Edit
    ) : TodoRecordAction

    data class AddSameAction(
        override val title: Int = TodoAddSameActionTitle,
        override val imageResId: Int = ArrowRight
    ) : TodoRecordAction

    data class DeleteAction(
        override val title: Int = TodoDeleteActionTitle,
        override val imageResId: Int = Delete
    ) : TodoRecordAction

    companion object {

        val actionList: ImmutableList<TodoRecordAction> = persistentListOf(
            EditAction(), AddSameAction(), DeleteAction(),
        )

        val filteredActionList: ImmutableList<TodoRecordAction> =
            actionList.filterNot { it is AddSameAction }.toImmutableList()
    }
}


