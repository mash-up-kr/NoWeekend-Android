package team.noweekend.core.common.ui.calendar.model

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import team.noweekend.core.resource.R

enum class DayId {
    SUNDAY,
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
}

sealed interface Day {
    val dayId: DayId

    @get:StringRes
    val id: Int
    val color: Color

    data class Sunday(
        override val dayId: DayId = DayId.SUNDAY,
        override val id: Int = R.string.sunday,
        override val color: Color = Color.Black,
    ) : Day

    data class Monday(
        override val dayId: DayId = DayId.MONDAY,
        override val id: Int = R.string.monday,
        override val color: Color = Color.Black,
    ) : Day

    data class TuesDay(
        override val dayId: DayId = DayId.TUESDAY,
        override val id: Int = R.string.tuesday,
        override val color: Color = Color.Black,
    ) : Day

    data class Wednesday(
        override val dayId: DayId = DayId.WEDNESDAY,
        override val id: Int = R.string.wednesday,
        override val color: Color = Color.Black,
    ) : Day

    data class Thursday(
        override val dayId: DayId = DayId.THURSDAY,
        override val id: Int = R.string.thursday,
        override val color: Color = Color.Black,
    ) : Day

    data class Friday(
        override val dayId: DayId = DayId.FRIDAY,
        override val id: Int = R.string.friday,
        override val color: Color = Color.Black,
    ) : Day

    data class Saturday(
        override val dayId: DayId = DayId.SATURDAY,
        override val id: Int = R.string.saturday,
        override val color: Color = Color.Black,
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
