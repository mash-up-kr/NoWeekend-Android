package team.noweekend.core.design.system.core.component.tabbar.item.defaults

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.graphics.Color

@Immutable
class NWKNavigationBarItemColors(
    private val selectedIconColor: Color,
    private val selectedLabelColor: Color,
    private val unselectedIconColor: Color,
    private val unselectedLabelColor: Color,
    private val disabledIconColor: Color,
    private val disabledLabelColor: Color,
) {
    @Composable
    internal fun iconColor(selected: Boolean, enabled: Boolean): State<Color> {
        return rememberUpdatedState(
            newValue = when {
                !enabled -> disabledIconColor
                selected -> selectedIconColor
                else -> unselectedIconColor
            },
        )
    }

    @Composable
    internal fun labelColor(selected: Boolean, enabled: Boolean): State<Color> {
        return rememberUpdatedState(
            newValue = when {
                !enabled -> disabledLabelColor
                selected -> selectedLabelColor
                else -> unselectedLabelColor
            },
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as NWKNavigationBarItemColors

        if (selectedIconColor != other.selectedIconColor) return false
        if (selectedLabelColor != other.selectedLabelColor) return false
        if (unselectedIconColor != other.unselectedIconColor) return false
        if (unselectedLabelColor != other.unselectedLabelColor) return false
        if (disabledIconColor != other.disabledIconColor) return false
        if (disabledLabelColor != other.disabledLabelColor) return false

        return true
    }

    override fun hashCode(): Int {
        var result = selectedIconColor.hashCode()
        result = 31 * result + selectedLabelColor.hashCode()
        result = 31 * result + unselectedIconColor.hashCode()
        result = 31 * result + unselectedLabelColor.hashCode()
        result = 31 * result + disabledIconColor.hashCode()
        result = 31 * result + disabledLabelColor.hashCode()
        return result
    }
}
