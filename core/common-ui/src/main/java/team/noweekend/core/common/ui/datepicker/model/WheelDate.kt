package team.noweekend.core.common.ui.datepicker.model

data class WheelDate(
    val year: Int,
    val month: Int,
    val day: Int,
)

enum class DatePickerType {
    YearMonth, YearMonthDay
}
