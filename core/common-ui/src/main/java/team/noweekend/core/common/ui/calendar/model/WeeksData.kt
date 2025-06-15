package team.noweekend.core.common.ui.calendar.model

import kotlinx.collections.immutable.ImmutableList

data class WeeksData(
    val year: Int,
    val month: Int,
    /**
     * 월과 주의 데이터 관리를 위해 2차원 List 로 관리
     */
    val dateOfWeeks: ImmutableList<ImmutableList<DateOfWeek>>,
)
