package team.noweekend.core.domain.repository

interface LocationRepository {
    suspend fun postUserLocation(
        latitude: Float = 37.55616F,
        longitude: Float = 126.9723F,
    )
}
