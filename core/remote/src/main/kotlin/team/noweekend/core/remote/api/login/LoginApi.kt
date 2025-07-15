package team.noweekend.core.remote.api.login

import team.noweekend.core.remote.model.login.LoginRequestBody
import team.noweekend.core.remote.model.login.LoginResponse

interface LoginApi {
    suspend fun requestLogin(requestBody: LoginRequestBody): LoginResponse
}
