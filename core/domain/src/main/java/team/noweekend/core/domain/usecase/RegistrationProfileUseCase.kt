package team.noweekend.core.domain.usecase

import team.noweekend.core.domain.repository.OnboardRepository
import team.noweekend.core.model.onboard.OnboardProfileParam
import javax.inject.Inject

class RegistrationProfileUseCase @Inject constructor(
    private val onboardRepository: OnboardRepository,
) {
    suspend operator fun invoke(param: OnboardProfileParam): Result<Unit> {
        return runCatching { onboardRepository.requestProfile(param) }
    }
}
