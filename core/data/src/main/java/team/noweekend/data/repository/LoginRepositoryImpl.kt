package team.noweekend.data.repository

import team.noweekend.core.domain.repository.LoginRepository
import team.noweekend.core.model.login.LoginInfoDomainModel
import team.noweekend.core.model.login.LoginRequest
import team.noweekend.core.remote.api.LoginApi
import team.noweekend.core.remote.model.LoginRequestBody
import team.noweekend.data.model.toDataModel
import javax.inject.Inject

internal class LoginRepositoryImpl @Inject constructor(
    private val loginApi: LoginApi,
) : LoginRepository {
    override suspend fun requestGoogleLogin(requestBody: LoginRequest): LoginInfoDomainModel {
        return loginApi.requestLogin(
            requestBody = LoginRequestBody(
                authorizationCode = requestBody.authorizationCode,
                name = requestBody.name,
            ),
        ).toDataModel()
            .toDomainModel()
    }
}
