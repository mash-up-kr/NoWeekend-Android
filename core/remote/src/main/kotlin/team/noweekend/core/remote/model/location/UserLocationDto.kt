package team.noweekend.core.remote.model.location

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserLocationDto(
    @SerialName("latitude")
    val latitude: Float,
    @SerialName("longitude")
    val longitude: Float,
)
