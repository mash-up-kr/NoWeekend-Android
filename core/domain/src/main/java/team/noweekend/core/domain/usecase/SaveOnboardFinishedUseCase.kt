package team.noweekend.core.domain.usecase

import team.noweekend.core.domain.repository.OnboardRepository
import javax.inject.Inject

class SaveOnboardFinishedUseCase @Inject constructor(
    private val onboardRepository: OnboardRepository,
) {
    suspend operator fun invoke(isFinished: Boolean) {
        runCatching { onboardRepository.setOnboardFinished(isFinished = isFinished) }
    }
}
