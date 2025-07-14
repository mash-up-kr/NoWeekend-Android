package team.noweekend.core.domain.usecase

import team.noweekend.core.domain.repository.AuthRepository
import javax.inject.Inject

class SaveAccessTokenUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke(accessToken: String) {
        authRepository.setAccessToken(token = accessToken)
    }
}
