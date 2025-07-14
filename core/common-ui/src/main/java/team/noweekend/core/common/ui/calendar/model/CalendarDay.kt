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
sealed interface CalendarDay {
    val dayId: DayOfWeek

    val id: Int
        @StringRes get

    val color: Color
        @Composable get() = NWKTheme.color.Neutral.neutralGray600

    data class Sunday(
        override val dayId: DayOfWeek = DayOfWeek.SUNDAY,
        override val id: Int = NWKStringResource.Sunday,
    ) : CalendarDay

    data class Monday(
        override val dayId: DayOfWeek = DayOfWeek.MONDAY,
        override val id: Int = NWKStringResource.Monday,
    ) : CalendarDay

    data class TuesCalendarDay(
        override val dayId: DayOfWeek = DayOfWeek.TUESDAY,
        override val id: Int = NWKStringResource.Tuesday,
    ) : CalendarDay

    data class Wednesday(
        override val dayId: DayOfWeek = DayOfWeek.WEDNESDAY,
        override val id: Int = NWKStringResource.Wednesday,
    ) : CalendarDay

    data class Thursday(
        override val dayId: DayOfWeek = DayOfWeek.THURSDAY,
        override val id: Int = NWKStringResource.Thursday,
    ) : CalendarDay

    data class Friday(
        override val dayId: DayOfWeek = DayOfWeek.FRIDAY,
        override val id: Int = NWKStringResource.Friday,
    ) : CalendarDay

    data class Saturday(
        override val dayId: DayOfWeek = DayOfWeek.SATURDAY,
        override val id: Int = NWKStringResource.Saturday,
    ) : CalendarDay

    companion object {
        private val defaultDays = persistentListOf(
            Sunday(),
            Monday(),
            TuesCalendarDay(),
            Wednesday(),
            Thursday(),
            Friday(),
            Saturday(),
        )

        private val monthDayStartedDays = persistentListOf(
            Monday(),
            TuesCalendarDay(),
            Wednesday(),
            Thursday(),
            Friday(),
            Saturday(),
            Sunday(),
        )

        fun getDays(isMondayStarted: Boolean): ImmutableList<CalendarDay> {
            return if (isMondayStarted) monthDayStartedDays else defaultDays
        }
    }
}
