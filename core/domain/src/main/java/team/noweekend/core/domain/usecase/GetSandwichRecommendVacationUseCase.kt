package team.noweekend.core.domain.usecase

import team.noweekend.core.domain.repository.RecommendRepository
import team.noweekend.core.model.vacation.SandwichRecommendVacation
import javax.inject.Inject

class GetSandwichRecommendVacationUseCase @Inject constructor(
    private val repository: RecommendRepository,
) {
    suspend operator fun invoke(): Result<SandwichRecommendVacation> = runCatching {
        repository.getSandwichRecommendVacation()
    }
}
