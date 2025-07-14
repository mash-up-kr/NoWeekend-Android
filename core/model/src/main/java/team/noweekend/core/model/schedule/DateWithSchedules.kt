package team.noweekend.core.model.schedule

data class DateWithSchedules(
    /**
     * 날짜 YYYY-MM-DD
     */
    val date: String,
    /**
     * 해당 날짜 온도
     */
    val dailyTemperature: Int,
    /**
     * 해당 날짜에 있는 스케줄 정보
     */
    val schedules: List<Schedule>
)
