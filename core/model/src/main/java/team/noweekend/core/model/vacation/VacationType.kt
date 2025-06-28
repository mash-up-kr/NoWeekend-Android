package team.noweekend.core.model.vacation

/**
 * 휴가 종류 정의
 * - [HOLIDAY_EXIST] 공휴일
 * - [HOLIDAY_EXIST_NOT] 공휴일 없음
 * - [INCLUDE_MONDAY] 월요일 포함 연차
 * - [INCLUDE_FRIDAY] 금요일 포함 연차
 * - [INCLUDE_WEEKEND] 공휴일 포함 연차
 * - [BIRTHDAY_EXIST] 생일
 *
 * @author JaesungLeee
 */
enum class VacationType(val number: Int) {
    HOLIDAY_EXIST(number = 0),
    HOLIDAY_EXIST_NOT(number = 1),
    INCLUDE_MONDAY(number = 2),
    INCLUDE_FRIDAY(number = 3),
    INCLUDE_WEEKEND(number = 4),
    BIRTHDAY_EXIST(number = 5),
    ;
}
