package team.noweekend.core.remote.model.onboard

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OnboardProfileRequest(
    @SerialName("nickname")
    val nickname: String,
    @SerialName("birthDate")
    val birthDate: String,
)
