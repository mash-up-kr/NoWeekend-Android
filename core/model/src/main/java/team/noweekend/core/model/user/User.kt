package team.noweekend.core.model.user

data class User(
    val userId: String,
    val userEmail: String,
    val userName: String,
    val userGender: String,
    val userBirth: String,
    val oAuthId: String,
    val oAuthType: String,
    val revocableToken: String,
    val role: String,
    val remainingAnnualLeave: Float,
    val latitude: Float,
    val longitude: Float,
    val averageTemperature: Float,
)
