package team.noweekend.core.remote.api.location

import io.ktor.client.HttpClient
import team.noweekend.core.remote.base.postApiCall
import team.noweekend.core.remote.model.location.UserLocationRequestDto
import team.noweekend.core.remote.qualifier.BasicClient
import javax.inject.Inject

class LocationApiImpl @Inject constructor(
    @BasicClient val client: HttpClient,
) : LocationApi {
    override suspend fun postUserLocation(latitude: Float, longitude: Float) {
        client.postApiCall<String>(
            path = "/api/v1/user/location",
            body = UserLocationRequestDto(latitude = latitude, longitude = longitude),
        )
    }
}
