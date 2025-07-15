package team.noweekend.core.domain.usecase

import team.noweekend.core.domain.repository.OnboardRepository
import team.noweekend.core.model.onboard.OnboardTags
import javax.inject.Inject

class GetOnboardTagsUseCase @Inject constructor(
    private val onboardRepository: OnboardRepository,
) {
    suspend operator fun invoke(): Result<OnboardTags> = runCatching { onboardRepository.getTagList() }
}
