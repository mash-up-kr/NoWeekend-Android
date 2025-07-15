package team.noweekend.core.domain.repository

import team.noweekend.core.model.user.User

interface UserRepository {
    suspend fun getUserProfile(): User
}
