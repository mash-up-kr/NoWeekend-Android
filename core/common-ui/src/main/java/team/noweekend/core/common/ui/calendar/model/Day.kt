package team.noweekend.core.common.ui.calendar.model

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.datetime.DayOfWeek
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.core.resource.NWKStringResource

@Stable
sealed interface Day {
    val dayId: DayOfWeek

    val id: Int
        @StringRes get

    val color: Color
        @Composable get() = NWKTheme.color.Neutral.neutralGray600

    data class Sunday(
        override val dayId: DayOfWeek = DayOfWeek.SUNDAY,
        override val id: Int = NWKStringResource.Sunday,
    ) : Day

    data class Monday(
        override val dayId: DayOfWeek = DayOfWeek.MONDAY,
        override val id: Int = NWKStringResource.Monday,
    ) : Day

    data class TuesDay(
        override val dayId: DayOfWeek = DayOfWeek.TUESDAY,
        override val id: Int = NWKStringResource.Tuesday,
    ) : Day

    data class Wednesday(
        override val dayId: DayOfWeek = DayOfWeek.WEDNESDAY,
        override val id: Int = NWKStringResource.Wednesday,
    ) : Day

    data class Thursday(
        override val dayId: DayOfWeek = DayOfWeek.THURSDAY,
        override val id: Int = NWKStringResource.Thursday,
    ) : Day

    data class Friday(
        override val dayId: DayOfWeek = DayOfWeek.FRIDAY,
        override val id: Int = NWKStringResource.Friday,
    ) : Day

    data class Saturday(
        override val dayId: DayOfWeek = DayOfWeek.SATURDAY,
        override val id: Int = NWKStringResource.Saturday,
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
