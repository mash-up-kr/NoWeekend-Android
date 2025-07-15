package team.noweekend.core.domain.usecase

import team.noweekend.core.domain.repository.LocationRepository
import javax.inject.Inject

class UserLocationUseCase @Inject constructor(
    private val repository: LocationRepository,
) {
    /**
     * 서울역 기준 위도 경도
     */
    suspend fun saveLocation(
        latitude: Float = 37.55616F,
        longitude: Float = 126.9723F,
    ): Result<Unit> = runCatching {
        repository.postUserLocation(latitude, longitude)
    }
}
