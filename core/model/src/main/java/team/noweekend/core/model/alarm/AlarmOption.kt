package team.noweekend.core.model.alarm

/**
 * 알람이 울려야하는 시점을 나타내는 옵션
 */
enum class AlarmOption {
    /**
     * 알람 옵션 없음
     */
    NONE,

    /**
     * 스케줄 하루 전
     */
    ONE_DAY_BEFORE,

    /**
     * 스케줄 2시간 전
     */
    TWO_HOURS_BEFORE,

    /**
     * 스케줄 1시간 전
     */
    ONE_HOUR_BEFORE,

    /**
     * 스케줄 30분 전
     */
    THIRTY_MINUTES_BEFORE,

    /**
     * 스케줄 15분 전
     */
    FIFTEEN_MINUTES_BEFORE,

    /**
     * 스케줄 5분 전
     */
    FIVE_MINUTES_BEFORE,

    /**
     * 스케줄 1분 전
     */
    ONE_MINUTE_BEFORE,
    ;
}
