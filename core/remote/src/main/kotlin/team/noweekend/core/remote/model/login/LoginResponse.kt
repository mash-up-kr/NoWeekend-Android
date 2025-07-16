package team.noweekend.core.remote.model.login

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    @SerialName("exists")
    val exists: Boolean,
    @SerialName("accessToken")
    val accessToken: String,
)
