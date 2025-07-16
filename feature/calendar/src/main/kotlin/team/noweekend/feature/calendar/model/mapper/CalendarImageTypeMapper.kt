package team.noweekend.feature.calendar.model.mapper

import team.noweekend.core.common.ui.calendar.model.CalendarImageType
import team.noweekend.core.model.calendar.ImageType

fun ImageType.toImageTypeWithId(): CalendarImageType {
    return when (this) {
        ImageType.NONE -> CalendarImageType.NONE
        ImageType.REST -> CalendarImageType.Rest
        ImageType.BURN_OUT -> CalendarImageType.BurnOut
        ImageType.FUTURE_SCHEDULE -> CalendarImageType.FutureSchedule
        ImageType.OVER_FIFTY_UNDER_SEVENTY_FIVE_DEGREE -> CalendarImageType.OverFiftyUnderSeventyFiveDegree
        ImageType.OVER_ZERO_UNDER_FIFTY_DEGREE -> CalendarImageType.OverZeroUnderFiftyDegree
    }
}
