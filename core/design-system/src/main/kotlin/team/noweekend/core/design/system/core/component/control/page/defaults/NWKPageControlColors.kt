package team.noweekend.core.design.system.core.component.control.page.defaults

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.graphics.Color

@Stable
internal interface PageControlColors {
    @Composable
    fun backgroundColor(isSelected: Boolean): State<Color>
}

@Immutable
internal class NWKPageControlColors(
    private val selectedBackgroundColor: Color,
    private val unSelectedBackgroundColor: Color,
) : PageControlColors {

    @Composable
    override fun backgroundColor(isSelected: Boolean): State<Color> {
        val targetValue = when {
            isSelected -> selectedBackgroundColor
            else -> unSelectedBackgroundColor
        }

        return rememberUpdatedState(newValue = targetValue)
    }

    override fun equals(other: Any?): Boolean {
        return this === other || (other is NWKPageControlColors &&
            selectedBackgroundColor == other.selectedBackgroundColor &&
            unSelectedBackgroundColor == other.unSelectedBackgroundColor
            )
    }

    override fun hashCode(): Int {
        return arrayOf(selectedBackgroundColor, unSelectedBackgroundColor).contentHashCode()
    }
}
