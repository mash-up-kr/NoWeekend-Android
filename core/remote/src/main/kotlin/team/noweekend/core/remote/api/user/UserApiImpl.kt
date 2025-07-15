package team.noweekend.core.remote.api.user

import io.ktor.client.HttpClient
import team.noweekend.core.remote.base.getApiCall
import team.noweekend.core.remote.model.user.UserProfileResponse
import team.noweekend.core.remote.qualifier.BasicClient
import javax.inject.Inject

class UserApiImpl @Inject constructor(
    @BasicClient val client: HttpClient,
) : UserApi {
    override suspend fun getUserProfile(): UserProfileResponse {
        return client.getApiCall(path = "/api/v1/user")
    }
}
