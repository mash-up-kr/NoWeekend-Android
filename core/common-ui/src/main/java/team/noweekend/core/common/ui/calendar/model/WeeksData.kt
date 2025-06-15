package team.noweekend.core.common.ui.calendar.model

import kotlinx.collections.immutable.ImmutableList

data class WeeksData(
    val year: Int,
    val month: Int,
    val dateOfWeeks: ImmutableList<ImmutableList<DateOfWeek>>,
)
