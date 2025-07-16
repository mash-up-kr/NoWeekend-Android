package team.noweekend.core.domain.repository

import team.noweekend.core.model.login.LoginInfoDomainModel
import team.noweekend.core.model.login.LoginRequest

interface LoginRepository {
    suspend fun requestGoogleLogin(requestBody: LoginRequest): LoginInfoDomainModel
}
