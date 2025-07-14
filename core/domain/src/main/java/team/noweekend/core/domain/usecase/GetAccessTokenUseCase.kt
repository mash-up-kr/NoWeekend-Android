package team.noweekend.core.domain.usecase

import team.noweekend.core.domain.repository.AuthRepository
import javax.inject.Inject

class GetAccessTokenUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke(): Result<String> {
        return runCatching { authRepository.getAccessToken() }
    }
}
