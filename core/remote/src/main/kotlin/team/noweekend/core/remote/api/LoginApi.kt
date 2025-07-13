package team.noweekend.core.remote.api

import team.noweekend.core.remote.model.LoginRequestBody
import team.noweekend.core.remote.model.LoginResponse

interface LoginApi {
    suspend fun requestLogin(requestBody: LoginRequestBody): LoginResponse
}
