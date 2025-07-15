package team.noweekend.feature.detail.date.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import kotlinx.serialization.Serializable
import team.noweekend.core.design.system.foundation.theme.NWKTheme
import team.noweekend.core.resource.NWKDrawableResource.Degree100
import team.noweekend.core.resource.NWKDrawableResource.Degree25
import team.noweekend.core.resource.NWKDrawableResource.Degree50
import team.noweekend.core.resource.NWKDrawableResource.DegreeAnnualLeave
import team.noweekend.core.resource.NWKStringResource.DegreeCardAnnualLeaveTitle
import team.noweekend.core.resource.NWKStringResource.DegreeCardTitle

@Serializable
@Immutable
data class DegreeUIModel(
    val degree: Int,
    val isAnnualLeave: Boolean,
) {
    @DrawableRes
    fun getResourceImage(): Int {
        return when {
            isAnnualLeave -> DegreeAnnualLeave
            degree in 0..49 -> Degree25
            degree in 50..99 -> Degree50
            else -> Degree100
        }
    }

    @Composable
    fun getColor(): Color {
        return when {
            isAnnualLeave -> NWKTheme.color.TaskItem.annualLeave
            degree in 0..49 -> NWKTheme.color.Toast.toast500
            degree in 50..99 -> NWKTheme.color.Toast.toast700
            else -> NWKTheme.color.Toast.toast900
        }
    }

    @StringRes
    fun getTitle(): Int {
        return when {
            isAnnualLeave -> DegreeCardAnnualLeaveTitle
            else -> DegreeCardTitle
        }
    }
}
