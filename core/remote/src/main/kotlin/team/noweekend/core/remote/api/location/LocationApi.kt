package team.noweekend.core.remote.api.location

interface LocationApi {
    suspend fun postUserLocation(
        latitude: Float,
        longitude: Float,
    )
}
