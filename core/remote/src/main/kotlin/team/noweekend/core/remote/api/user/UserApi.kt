package team.noweekend.core.remote.api.user

import team.noweekend.core.remote.model.user.UserProfileResponse

interface UserApi {
    suspend fun getUserProfile(): UserProfileResponse
}
