package team.noweekend.data.repository

import team.noweekend.core.domain.repository.AuthRepository
import team.noweekend.core.domain.repository.LocationRepository
import team.noweekend.core.domain.repository.LoginRepository
import team.noweekend.core.model.login.LoginInfoDomainModel
import team.noweekend.core.model.login.LoginRequest
import team.noweekend.core.remote.api.login.LoginApi
import team.noweekend.core.remote.model.login.LoginRequestBody
import team.noweekend.data.model.toDataModel
import javax.inject.Inject

internal class LoginRepositoryImpl @Inject constructor(
    private val loginApi: LoginApi,
    private val authRepository: AuthRepository,
    private val locationRepository: LocationRepository,
) : LoginRepository {
    override suspend fun requestGoogleLogin(requestBody: LoginRequest): LoginInfoDomainModel {
        return loginApi.requestLogin(
            requestBody = LoginRequestBody(
                authorizationCode = requestBody.authorizationCode,
                name = requestBody.name,
            ),
        ).also {
            authRepository.setAccessToken(token = it.accessToken)
            locationRepository.postUserLocation()
        }
            .toDataModel()
            .toDomainModel()
    }
}
