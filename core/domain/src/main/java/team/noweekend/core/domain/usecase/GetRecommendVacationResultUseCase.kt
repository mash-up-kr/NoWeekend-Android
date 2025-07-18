package team.noweekend.core.domain.usecase

import team.noweekend.core.domain.repository.RecommendRepository
import team.noweekend.core.model.vacation.RecommendVacationResult
import javax.inject.Inject

class GetRecommendVacationResultUseCase @Inject constructor(
    private val repository: RecommendRepository,
) {
    suspend operator fun invoke(): Result<RecommendVacationResult> = runCatching {
        repository.getRecommendVacation()
    }
}
