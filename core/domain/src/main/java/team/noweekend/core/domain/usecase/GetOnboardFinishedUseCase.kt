package team.noweekend.core.domain.usecase

import team.noweekend.core.domain.repository.OnboardRepository
import javax.inject.Inject

class GetOnboardFinishedUseCase @Inject constructor(
    private val onboardRepository: OnboardRepository,
) {
    suspend operator fun invoke(): Result<Boolean> {
        return runCatching { onboardRepository.getIsOnboardFinished() }
    }
}
