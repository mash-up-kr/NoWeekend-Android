package team.noweekend.feature.login.model

import team.noweekend.core.model.login.LoginRequest

data class UserLoginInfo(
    val userName: String,
    val authToken: String,
) {
    fun toLoginRequest(): LoginRequest {
        return LoginRequest(
            authorizationCode = authToken,
            name = userName,
        )
    }
}
