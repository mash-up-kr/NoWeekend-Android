package team.noweekend.core.domain.usecase

import team.noweekend.core.domain.repository.RecommendRepository
import javax.inject.Inject

class CreateVacationUseCase @Inject constructor(
    private val repository: RecommendRepository,
) {
    suspend operator fun invoke(
        days: Int,
        travelStyle: String,
        activityType: String,
        restPreference: String,
        leisurePreference: String,
    ): Result<Unit> = runCatching {
        repository.postRecommendVacation(days, travelStyle, activityType, restPreference, leisurePreference)
    }
}
