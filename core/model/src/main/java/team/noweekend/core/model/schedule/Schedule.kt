package team.noweekend.core.model.schedule

import kotlinx.serialization.Serializable
import team.noweekend.core.model.alarm.AlarmOption

/**
 * 스케줄 정보
 */
@Serializable
data class Schedule(
    /**
     * 스케줄 구분 id
     */
    val id: String,
    /**
     * 스케줄 제목
     */
    val title: String,
    /**
     * 스케줄 시작 시간
     */
    val startTime: String,
    /**
     * 스케줄 종료 시간
     */
    val endTime: String,
    /**
     * 스케줄 타입 [ScheduleCategory] 중 하나
     */
    val category: ScheduleCategory,
    /**
     * 열정 온도
     */
    val temperature: Int,
    /**
     * 종일 여부
     */
    val allDay: Boolean,
    /**
     * 완료 여부
     */
    val completed: Boolean,
    /**
     * 스케줄 알림 옵션 [AlarmOption] 중 하나
     */
    val alarmOption: AlarmOption,
)
