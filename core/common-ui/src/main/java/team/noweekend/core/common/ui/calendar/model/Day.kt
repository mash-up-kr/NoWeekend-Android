package team.noweekend.core.common.ui.calendar.model

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.datetime.DayOfWeek
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.core.resource.R

@Stable
sealed interface Day {
    val dayId: DayOfWeek

    val id: Int
        @StringRes get

    val color: Color
        @Composable get () = NWKTheme.color.Neutral.neutralGray600

    data class Sunday(
        override val dayId: DayOfWeek = DayOfWeek.SUNDAY,
        override val id: Int = R.string.sunday,
    ) : Day

    data class Monday(
        override val dayId: DayOfWeek = DayOfWeek.MONDAY,
        override val id: Int = R.string.monday,
    ) : Day

    data class TuesDay(
        override val dayId: DayOfWeek = DayOfWeek.TUESDAY,
        override val id: Int = R.string.tuesday,
    ) : Day

    data class Wednesday(
        override val dayId: DayOfWeek = DayOfWeek.WEDNESDAY,
        override val id: Int = R.string.wednesday,
    ) : Day

    data class Thursday(
        override val dayId: DayOfWeek = DayOfWeek.THURSDAY,
        override val id: Int = R.string.thursday,
    ) : Day

    data class Friday(
        override val dayId: DayOfWeek = DayOfWeek.FRIDAY,
        override val id: Int = R.string.friday,
    ) : Day

    data class Saturday(
        override val dayId: DayOfWeek = DayOfWeek.SATURDAY,
        override val id: Int = R.string.saturday,
    ) : Day

    companion object {
        private val defaultDays = persistentListOf(
            Sunday(),
            Monday(),
            TuesDay(),
            Wednesday(),
            Thursday(),
            Friday(),
            Saturday(),
        )

        private val monthDayStartedDays = persistentListOf(
            Monday(),
            TuesDay(),
            Wednesday(),
            Thursday(),
            Friday(),
            Saturday(),
            Sunday(),
        )

        fun getDays(isMondayStarted: Boolean): ImmutableList<Day> {
            return if (isMondayStarted) monthDayStartedDays else defaultDays
        }
    }
}
