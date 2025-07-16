package team.noweekend.feature.create.vacation.information.component.button.defaults

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.graphics.Color

@Immutable
class InformationSelectRadioButtonColors(
    private val contentColor: Color,
    private val borderColor: Color,
    private val unselectedContentColor: Color,
    private val unselectedBorderColor: Color,
) {

    @Composable
    internal fun contentColor(selected: Boolean): State<Color> {
        return rememberUpdatedState(
            newValue = if (selected) contentColor else unselectedContentColor,
        )
    }

    @Composable
    internal fun borderColor(selected: Boolean): State<Color> {
        return rememberUpdatedState(
            newValue = if (selected) borderColor else unselectedBorderColor,
        )
    }

    override fun equals(other: Any?): Boolean {
        return this === other || (other is InformationSelectRadioButtonColors &&
            contentColor == other.contentColor &&
            borderColor == other.borderColor &&
            unselectedContentColor == other.unselectedContentColor &&
            unselectedBorderColor == other.unselectedBorderColor
            )
    }

    override fun hashCode(): Int {
        return arrayOf(contentColor, borderColor, unselectedContentColor, unselectedBorderColor).contentHashCode()
    }
}
