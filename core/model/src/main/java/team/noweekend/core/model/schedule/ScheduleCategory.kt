package team.noweekend.core.model.schedule

/**
 * 스케줄 타입
 */
enum class ScheduleCategory(val tag: String) {
    /**
     * 회사 일정
     */
    COMPANY(tag = "회사"),

    /**
     * 개인 일정
     */
    PERSONAL(tag = "개인"),

    /**
     * 기타 일정
     */
    ETC(tag = "기타"),

    /**
     * 휴가 일정
     */
    LEAVE(tag = "연차"),
    ;
}
