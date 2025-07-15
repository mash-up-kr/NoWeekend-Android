package team.noweekend.core.model.calendar

data class WeeksData(
    val year: Int,
    val month: Int,
    /**
     * 월과 주의 데이터 관리를 위해 2차원 List 로 관리
     */
    val dateOfWeeks: List<List<DateOfWeek>>,
)
