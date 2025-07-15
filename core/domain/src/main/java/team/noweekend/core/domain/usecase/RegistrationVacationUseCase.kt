package team.noweekend.core.domain.usecase

import team.noweekend.core.domain.repository.OnboardRepository
import team.noweekend.core.model.onboard.OnboardVacationParam
import javax.inject.Inject

class RegistrationVacationUseCase @Inject constructor(
    private val onboardRepository: OnboardRepository,
) {
    suspend operator fun invoke(param: OnboardVacationParam): Result<Unit> {
        return runCatching { onboardRepository.requestVacation(param) }
    }
}
