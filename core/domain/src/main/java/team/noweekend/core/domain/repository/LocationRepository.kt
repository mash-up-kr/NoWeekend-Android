package team.noweekend.core.domain.repository

interface LocationRepository {
    suspend fun postUserLocation(
        latitude: Float,
        longitude: Float,
    )
}
