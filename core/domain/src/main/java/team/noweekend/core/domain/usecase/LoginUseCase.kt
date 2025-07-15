package team.noweekend.core.domain.usecase

import team.noweekend.core.domain.repository.LoginRepository
import team.noweekend.core.model.login.LoginInfoDomainModel
import team.noweekend.core.model.login.LoginRequest
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: LoginRepository,
) {
    suspend operator fun invoke(requestBody: LoginRequest): Result<LoginInfoDomainModel> =
        runCatching { repository.requestGoogleLogin(requestBody) }
}
