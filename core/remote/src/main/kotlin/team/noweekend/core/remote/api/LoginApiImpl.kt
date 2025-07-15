package team.noweekend.core.remote.api

import io.ktor.client.HttpClient
import team.noweekend.core.remote.base.postApiCall
import team.noweekend.core.remote.model.LoginRequestBody
import team.noweekend.core.remote.model.LoginResponse
import team.noweekend.core.remote.qualifier.BasicClient
import javax.inject.Inject

internal class LoginApiImpl @Inject constructor(
    @BasicClient val client: HttpClient,
) : LoginApi {
    override suspend fun requestLogin(requestBody: LoginRequestBody): LoginResponse {
        return client.postApiCall(
            path = "api/v1/login/GOOGLE",
            body = requestBody,
        )
    }
}
