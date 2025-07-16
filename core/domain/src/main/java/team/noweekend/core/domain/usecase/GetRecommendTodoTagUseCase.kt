package team.noweekend.core.domain.usecase

import team.noweekend.core.domain.repository.RecommendRepository
import javax.inject.Inject

class GetRecommendTodoTagUseCase @Inject constructor(
    private val recommendRepository: RecommendRepository,
) {
    suspend operator fun invoke(): List<String> = recommendRepository.getRecommendTodoTag()
}
