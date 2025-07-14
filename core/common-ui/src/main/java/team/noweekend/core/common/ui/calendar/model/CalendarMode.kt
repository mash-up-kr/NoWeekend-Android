package team.noweekend.core.common.ui.calendar.model

import androidx.annotation.StringRes
import team.noweekend.core.resource.NWKStringResource

enum class CalendarMode(@StringRes val id: Int) {
    WEEK(id = NWKStringResource.Week), MONTH(id = NWKStringResource.Month)
}
