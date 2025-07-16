package team.noweekend.core.common.ui.datepicker.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import team.noweekend.core.common.ui.datepicker.model.DatePickerType

class PreviewWheelDatePickerParameterProvider : PreviewParameterProvider<DatePickerType> {
    override val values = sequenceOf(
        DatePickerType.YearMonthDay,
        DatePickerType.YearMonth,
    )
}
