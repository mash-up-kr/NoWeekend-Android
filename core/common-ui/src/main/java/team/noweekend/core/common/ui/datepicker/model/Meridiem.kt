package team.noweekend.core.common.ui.datepicker.model

import androidx.annotation.StringRes
import team.noweekend.core.resource.NWKStringResource.AnteMeridiem
import team.noweekend.core.resource.NWKStringResource.PostMeridiem

/**
 * AM Ante [Meridiem]
 * PM Post [Meridiem]
 */
enum class Meridiem(
    @StringRes val id: Int,
) {
    AM(id = AnteMeridiem), PM(id = PostMeridiem)
}
