package team.noweekend.core.remote.model.onboard

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OnboardVacationRequest(
    @SerialName("days")
    val days: Int,
    @SerialName("hours")
    val hours: Int,
)
