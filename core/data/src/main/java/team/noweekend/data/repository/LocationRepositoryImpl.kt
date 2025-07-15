package team.noweekend.data.repository

import team.noweekend.core.domain.repository.LocationRepository
import team.noweekend.core.remote.api.location.LocationApi
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val locationApi: LocationApi
) : LocationRepository {
    override suspend fun postUserLocation(latitude: Float, longitude: Float) {
        locationApi.postUserLocation(latitude, longitude)
    }
}
