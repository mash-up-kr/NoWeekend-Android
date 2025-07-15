package team.noweekend.core.domain.usecase

import team.noweekend.core.domain.repository.OnboardRepository
import team.noweekend.core.model.onboard.OnboardTagsParam
import javax.inject.Inject

class RegistrationTagsUseCase @Inject constructor(
    private val onboardRepository: OnboardRepository,
) {
    suspend operator fun invoke(param: OnboardTagsParam): Result<Unit> {
        return runCatching { onboardRepository.requestTags(param) }
    }
}
