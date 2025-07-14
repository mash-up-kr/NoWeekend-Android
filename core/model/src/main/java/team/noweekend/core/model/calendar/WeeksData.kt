package team.noweekend.core.model.calendar

import kotlinx.datetime.LocalDate

data class WeeksData(
    val year: Int,
    val month: Int,
    /**
     * 월과 주의 데이터 관리를 위해 2차원 List 로 관리
     */
    val dateOfWeeks: List<List<DateOfWeek>>,
) {
//    companion object {
//
//        private val now = LocalDate.now()
//
//        val default = WeeksData(
//            year = now.year,
//            month = now.monthNumber,
//            dateOfWeeks = persistentListOf(),
//        )
//    }
}

