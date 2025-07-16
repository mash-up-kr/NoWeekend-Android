package team.noweekend.data.repository

import team.noweekend.core.domain.repository.UserRepository
import team.noweekend.core.model.user.User
import team.noweekend.core.remote.api.user.UserApi
import team.noweekend.data.mapper.toDomain
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userApi: UserApi,
) : UserRepository {
    override suspend fun getUserProfile(): User {
        return userApi.getUserProfile().toDomain()
    }
}
