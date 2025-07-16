package team.noweekend.data.mapper

import team.noweekend.core.model.user.User
import team.noweekend.core.remote.model.user.UserProfileResponse

internal fun UserProfileResponse.toDomain(): User = User(
    userId = userId,
    userEmail = userEmail,
    userName = userName,
    userGender = userGender,
    userBirth = userBirth,
    oAuthId = oAuthId,
    oAuthType = oAuthType,
    revocableToken = revocableToken,
    role = role,
    remainingAnnualLeave = remainingAnnualLeave,
    latitude = location?.latitude,
    longitude = location?.longitude,
    averageTemperature = averageTemperature,
)
