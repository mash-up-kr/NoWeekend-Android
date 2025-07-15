package team.noweekend.core.remote.model.user

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import team.noweekend.core.remote.model.location.UserLocationDto

@Serializable
data class UserProfileResponse(
    @SerialName("id")
    val userId: String,
    @SerialName("email")
    val userEmail: String,
    @SerialName("name")
    val userName: String,
    @SerialName("gender")
    val userGender: String,
    @SerialName("birthDate")
    val userBirth: String,
    @SerialName("providerId")
    val oAuthId: String,
    @SerialName("providerType")
    val oAuthType: String,
    @SerialName("revocableToken")
    val revocableToken: String,
    @SerialName("role")
    val role: String,
    @SerialName("remainingAnnualLeave")
    val remainingAnnualLeave: Float,
    @SerialName("location")
    val location: UserLocationDto,
    @SerialName("averageTemperature")
    val averageTemperature: Float,
)
