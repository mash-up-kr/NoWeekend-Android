package team.noweekend.core.model.login

data class LoginRequest(
    val authorizationCode: String,
    val name: String,
)
