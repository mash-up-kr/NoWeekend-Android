package team.noweekend.core.common.ui.calendar.model

import androidx.annotation.DrawableRes
import team.noweekend.core.resource.NWKDrawableResource

enum class CalendarImageType(@DrawableRes val id: Int) {
    NONE(id = NWKDrawableResource.DayTypeNone),
    FutureSchedule(id = NWKDrawableResource.DayTypeFutureSchedule),
    BurnOut(id = NWKDrawableResource.DayTypeBurnOut),
    Rest(id = NWKDrawableResource.DayTypeRest),
    OverZeroUnderFiftyDegree(id = NWKDrawableResource.DayTypeOverZeroUnderFiftyDegree),
    OverFiftyUnderSeventyFiveDegree(id = NWKDrawableResource.DayTypeOverFiftyUnderSeventyFiveDegree),
}
