package team.noweekend.core.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginRequestBody(
    @SerialName("authorizationCode")
    val authorizationCode: String,
    @SerialName("name")
    val name: String,
)
